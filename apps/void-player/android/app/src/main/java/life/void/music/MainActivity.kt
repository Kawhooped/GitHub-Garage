package life.void.music

import android.Manifest
import android.app.Activity
import android.content.ComponentName
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import androidx.media3.common.util.UnstableApi
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import org.json.JSONArray
import org.json.JSONObject

@UnstableApi
class MainActivity : Activity() {
    private lateinit var web: WebView
    private var controller: MediaController? = null
    private var controllerFuture: ListenableFuture<MediaController>? = null
    private var tracks: JSONArray = JSONArray()
    private var pendingQueue: List<MediaItem>? = null
    private val permission get() = if (Build.VERSION.SDK_INT >= 33) Manifest.permission.READ_MEDIA_AUDIO else Manifest.permission.READ_EXTERNAL_STORAGE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        web = WebView(this).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.mediaPlaybackRequiresUserGesture = false
            webChromeClient = WebChromeClient()
            webViewClient = WebViewClient()
            addJavascriptInterface(MusicBridge(), "AndroidMusic")
        }
        setContentView(web)
        web.loadUrl("file:///android_asset/index.html")
        connectPlayback()
    }

    private fun connectPlayback() {
        val token = SessionToken(this, ComponentName(this, PlaybackService::class.java))
        controllerFuture = MediaController.Builder(this, token).buildAsync()
        controllerFuture?.addListener({
            try {
                controller = controllerFuture?.get()
                pendingQueue?.let { controller?.setMediaItems(it); pendingQueue = null }
            } catch (_: Exception) { }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun hasPermission() = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

    private fun scanLibrary() {
        if (!hasPermission()) {
            runOnUiThread { requestPermissions(arrayOf(permission), 41) }
            return
        }
        val result = JSONArray()
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.SIZE
        )
        val sort = MediaStore.Audio.Media.TITLE + " COLLATE NOCASE ASC"
        val cursor: Cursor? = contentResolver.query(uri, projection, null, null, sort)
        cursor?.use { c ->
            val idCol=c.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val titleCol=c.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistCol=c.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val albumCol=c.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
            val durationCol=c.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val nameCol=c.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
            val sizeCol=c.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)
            while(c.moveToNext()) {
                val id=c.getLong(idCol)
                val contentUri=Uri.withAppendedPath(uri, id.toString())
                result.put(JSONObject().apply {
                    put("id", id.toString())
                    put("uri", contentUri.toString())
                    put("title", c.getString(titleCol) ?: "Unknown title")
                    put("artist", c.getString(artistCol) ?: "Unknown artist")
                    put("album", c.getString(albumCol) ?: "")
                    put("duration", c.getLong(durationCol))
                    put("name", c.getString(nameCol) ?: "")
                    put("size", c.getLong(sizeCol))
                })
            }
        }
        tracks=result
        runOnUiThread { deliverLibrary() }
    }

    private fun deliverLibrary() {
        val json=JSONObject.quote(tracks.toString())
        web.evaluateJavascript("window.onNativeLibrary && window.onNativeLibrary($json)", null)
    }

    private inner class MusicBridge {
        @JavascriptInterface fun scan() { scanLibrary() }
        @JavascriptInterface fun setQueue(json: String) {
            val arr=JSONArray(json)
            val list=ArrayList<MediaItem>()
            for(i in 0 until arr.length()) {
                val t=arr.getJSONObject(i)
                val id=t.optString("id")
                val row=(0 until tracks.length()).firstOrNull { tracks.getJSONObject(it).optString("id")==id }
                val source=if(row!=null) tracks.getJSONObject(row).optString("uri") else ""
                if(source.isNotBlank()) {
                    val metadata=MediaMetadata.Builder()
                        .setTitle(t.optString("title"))
                        .setArtist(t.optString("artist"))
                        .setAlbumTitle(t.optString("album"))
                        .build()
                    list.add(MediaItem.Builder().setMediaId(id).setUri(source).setMediaMetadata(metadata).build())
                }
            }
            runOnUiThread { if(controller!=null) controller?.setMediaItems(list) else pendingQueue=list }
        }
        @JavascriptInterface fun play(index: Int) { runOnUiThread { controller?.let { if(index in 0 until it.mediaItemCount){it.seekTo(index,0);it.prepare();it.play()} } } }
        @JavascriptInterface fun toggle() { runOnUiThread { controller?.let { if(it.isPlaying) it.pause() else {if(it.mediaItemCount>0){if(it.currentMediaItemIndex<0)it.seekTo(0,0);it.prepare();it.play()}} } } }
        @JavascriptInterface fun pause() { runOnUiThread { controller?.pause() } }
        @JavascriptInterface fun next() { runOnUiThread { controller?.seekToNextMediaItem();controller?.play() } }
        @JavascriptInterface fun previous() { runOnUiThread { controller?.seekToPreviousMediaItem();controller?.play() } }
        @JavascriptInterface fun seekTo(positionMs: Long) { runOnUiThread { controller?.seekTo(positionMs.coerceAtLeast(0)) } }
        @JavascriptInterface fun setVolume(value: Double) { runOnUiThread { controller?.volume=value.toFloat().coerceIn(0f,1f) } }
        @JavascriptInterface fun getState(): String {
            val c=controller ?: return JSONObject().put("index",-1).put("playing",false).toString()
            return JSONObject().apply {
                put("index",c.currentMediaItemIndex)
                put("playing",c.isPlaying)
                put("position",c.currentPosition)
                put("duration",if(c.duration>0)c.duration else 0)
            }.toString()
        }
    }

    override fun onRequestPermissionsResult(requestCode:Int, permissions:Array<out String>, grantResults:IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==41 && grantResults.firstOrNull()==PackageManager.PERMISSION_GRANTED) scanLibrary()
        else web.evaluateJavascript("document.getElementById('status').textContent='MUSIC ACCESS NOT GRANTED'",null)
    }

    override fun onDestroy() {
        controllerFuture?.let { MediaController.releaseFuture(it) }
        web.destroy()
        super.onDestroy()
    }
}
