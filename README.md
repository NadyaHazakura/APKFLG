# SafetyCoreBlocker — Android GitHub Actions template

This repository is a minimal Android project that builds an APK and publishes it to a GitHub Release.

Important: a normal third-party Android app cannot permanently prevent another package, such as GSC, from being installed or updated. This template therefore does **not** contain code intended to interfere with Android package management, Google Play, or device security.

## Build

Push a tag such as `v1.0.0`, or run the workflow manually from GitHub Actions.

The workflow builds:

`app/build/outputs/apk/debug/app-debug.apk`

and attaches it to the GitHub Release.

## Signing

The workflow currently produces a debug-signed APK. For a production release, configure an Android signing key through GitHub Actions secrets and build a release variant.
