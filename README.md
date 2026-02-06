# Tic Tac Toe Android Kata

A simple, Tic Tac Toe implementation for Android, built using Jetpack Compose

## Overview

This project is a coding kata focused on implementing the classic Tic Tac Toe game. It demonstrates:
- State management using Kotlin sealed classes.
- Game logic for win and draw detection.

## Project Structure

- `MainActivity.kt`: The entry point that hosts the game.
- `TicTacToeState.kt`: Contains the core game logic and state definitions (Sealed classes for game phases).
- `ui/TicTacToeScreen.kt`: The Compose-based UI for the game board and controls.

## Getting Started

1. Clone the repository.
2. Open the project in **Android Studio (Hedgehog or newer)**.
3. Sync the project with Gradle files.
4. Run the `app` module on an emulator or a physical device.

## Running the tests

### Unit tests
```
./gradlew testDebugUnitTest
```

### Integration tests
```
./gradlew connectedAndroidTest
```