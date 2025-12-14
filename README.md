# Image Downloader WS

A simple Android app that allows users to download images from a URL and display them in an ImageView.

## Features

- Enter the URL of an image to download
- Save the image locally in the app's private storage
- Display the downloaded image immediately in an ImageView
- Handles downloads on a background thread to keep the UI responsive
- Uses ConstraintLayout for flexible UI design

## Screenshots
<img width="400" height="900" alt="image downloader screenshot" src="https://github.com/user-attachments/assets/289110d2-7220-4f9c-8b0a-5f8b95b4579f" />

## How to Use

1. Open the app.
2. Enter a valid image URL in the input field.
3. Tap the "Fetch" button.
4. The image will be downloaded and displayed below.

## Project Structure

- **MainActivity.kt**: Main activity handling user input, downloading the image, and updating the UI.
- **activity_main.xml**: Layout with EditText for URL input, Button for fetching, and ImageView for displaying the image.

## Requirements

- Android Studio Arctic Fox or later
- Android device or emulator running Android 6.0 (API 23) or higher
- Internet access to download images

## Notes

- Images are stored in the app's private pictures directory (`getExternalFilesDir(Environment.DIRECTORY_PICTURES)`), so no storage permission is needed.
- Uses a background thread for network operations to avoid blocking the UI thread.
- Error handling is included for invalid URLs or download failures.

## License

This project is licensed under the MIT License.
