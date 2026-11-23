# VOID Player

A local-first browser music and video player.

## Launch
Open `index.html` in a modern browser, or publish this folder as a static site.

## Features
- Audio and video playback using the browser's native media engine.
- Add multiple files, drag and drop, or select a folder.
- Queue with search, remove, shuffle, repeat, next and previous.
- Seek, volume control, responsive video stage, and compact playback bar.
- Keyboard shortcuts: Space (play/pause), Left/Right (seek 5 seconds), J/K (previous/next).
- No account, server, tracking, or media upload. Files are read locally through temporary browser object URLs.

## Compatibility
Playback depends on the browser and device supporting the file's codec. Common MP3, AAC/M4A, WAV, OGG, MP4 (H.264/AAC), and WebM files are the safest choices. FLAC, MOV, and other formats vary by browser/device. The queue is session-only and is cleared when the page is closed.

## Publish
This is a static app with no build step. Enable GitHub Pages for the repository and publish from the `dd-main` branch; the app will be available at:

`https://kawhooped.github.io/GitHub-Garage/apps/void-player/`

If the repository is already deployed through Cloudflare Pages, the same path is served automatically after the commit is deployed.
