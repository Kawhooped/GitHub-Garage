# VOID Player

A local-first music, video and Suno radio organizer. Single-file static web app; no build step, account, backend or media upload.

## Features
- Play local audio and video using the browser's native media engine.
- Import multiple files, drag/drop, or select a folder.
- Searchable queue and library; play, remove and edit title, artist/persona and tags.
- Transport controls: seek, volume, shuffle, repeat, previous/next, keyboard shortcuts.
- Suno Radio: create named stations (mood, genre, project, era, etc.), add tracks, and play station queues.
- Add Suno share URLs as catalog entries; import the corresponding downloaded audio file to play it.
- Export/import station and catalog metadata as JSON.
- Responsive desktop and mobile layout.

## Use
Open `index.html` in a modern browser, or publish this folder as a static site:
`https://kawhooped.github.io/GitHub-Garage/apps/void-player/`

## Media and privacy
Media files are selected locally and played through temporary browser object URLs. They are not uploaded. The browser session owns the active file queue; closing/reloading the page requires reselecting the media files. Station/catalog metadata is stored in this browser's local storage and can be exported as JSON. Exported metadata does not contain the media itself.

Browser codec support varies. MP3, AAC/M4A, WAV, OGG, MP4 (H.264/AAC) and WebM are common safe choices. FLAC, MOV and other formats vary by browser/device.

## Suno workflow
1. Download a Suno generation as an audio file.
2. Import it into the library.
3. Edit its title, artist/persona and tags.
4. Create stations such as Night Drive, Instrumentals, Game Scores, Ambient, or Favorites.
5. Add the track to one or more stations and export your library metadata for backup.

Suno share links are catalog references only; this app does not scrape or stream audio from Suno URLs.


## Android phone-wide library

The Android companion app is in `android/`. Unlike the browser edition, it requests Android's audio-library permission and queries MediaStore, so it can discover music already indexed on the phone without manually selecting files. Playback runs through Android Media3 in a media session service, enabling background playback and system media controls.

### Build
A GitHub Actions workflow builds a debug APK when Android source changes. Open the repository's Actions tab, select **Build VOID Music Android**, and download the `void-music-android` artifact after a successful run. Install the APK on Android, open VOID Music, and grant music/audio access when prompted.

The APK is a debug build for personal installation, not a Play Store release. Music remains on the phone; the app reads MediaStore metadata and plays the original content URIs locally.
