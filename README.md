# TPStreams Android Demo App

A modern Android application demonstrating the integration of the **TPStreams Player SDK**. This app allows users to securely stream DRM-protected videos by providing an organization code, asset ID, and access token.

## 🚀 Features

- **TPStreams SDK Integration**: Full implementation of the TPStreams Player for high-quality, secure video playback.
- **Modern Dark UI**: A premium, immersive dark-themed interface built with **Jetpack Compose**.
- **Secure Playback**: Supports DRM-protected content via TPStreams infrastructure.
- **Custom Branding**: Fully customized with TPStreams logos and unified app icons.
- **Responsive Player**: A centered 16:9 video player with a dedicated black-out UI for an cinematic experience.
- **Edge-to-Edge Experience**: Utilizes the full screen height for modern Android devices.

## 🛠 Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **SDK**: [TPStreams Android SDK v3.1.6](https://developer.tpstreams.com/docs/mobile-sdk/android-native-sdk/getting-started)
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 35 (Android 15)
- **Build System**: Gradle (Kotlin DSL)

## 📦 Getting Started

### Prerequisites

- Android Studio Flamingo or newer.
- A valid TPStreams Organization Code and Asset credentials.

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/TPStreamsAndroid.git
   ```
2. Open the project in Android Studio.
3. Sync Gradle and build the project.
4. Run the app on your device or emulator.

## 📖 Usage

1. **Initialize**: Enter your **Organization Code** on the main screen.
2. **Authorize**: Provide the **Asset ID** and **Access Token** for the video you wish to play.
3. **Play**: Tap the "Play" button to launch the immersive player and start streaming.

## 🏗 Project Structure

- `MainActivity.kt`: Handles user input and SDK initialization using Jetpack Compose.
- `PlayerActivity.kt`: A dedicated AppCompatActivity hosting the `TpStreamPlayerFragment`.
- `activity_player.xml`: Optimized layout for centering the video with aspect ratio constraints.
- `settings.gradle.kts`: Configured with the TPStreams Maven repository.

## 📄 License

This project is for demonstration purposes. Refer to [TPStreams](https://tpstreams.com/) for SDK licensing and usage terms.
