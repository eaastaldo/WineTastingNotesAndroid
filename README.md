# Wine Tasting Notes — Android

English Android app based on the user's systematic wine tasting sheet.

## Features
- Wine details and tasting archive
- Appearance / Nose / Palate / Conclusions
- Primary, secondary and tertiary aroma selectors
- Personal score and buy-again decision
- Offline local storage
- PDF summary export
- Long-press a tasting to delete it

## Build
Requires Android SDK 35 and Gradle 8.9.

```bash
gradle assembleDebug
```

APK output:
`app/build/outputs/apk/debug/app-debug.apk`

A GitHub Actions workflow is included at `.github/workflows/android.yml`.
