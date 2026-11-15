# Little Image Studio

A lightweight, mobile-friendly image workbench served directly from GitHub Pages.

## Included now
- Prompt editor and selectable visual moods
- Random prompt and surprise-style controls
- Image import from your device
- Square preview canvas
- PNG export normalized to exactly 1026 × 1026 pixels
- Browser-local gallery (up to 24 images)
- Configurable generation endpoint; defaults to `/api/generate`

## Image generation connection

The interface expects a secure server endpoint accepting:

```http
POST /api/generate
Content-Type: application/json

{"prompt":"...","size":"1026x1026"}
```

The endpoint should return JSON containing one of `image`, `image_url`, `url`, or `b64_json`. Keep provider API keys on the server. Never put secret keys in this static page.

Until a generation endpoint is connected, image import, preview, gallery, and PNG export work independently. The gallery is stored in this browser's local storage and does not sync between devices.

## Open

Visit the GitHub Pages Garage site and choose Little Image Studio, or open the project folder's `index.html` through a static web host.
