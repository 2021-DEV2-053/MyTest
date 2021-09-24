# TicTacToe - Test
TicTacToe (Android app) is a game for two players, X and O, who take turns marking the spaces in a 3×3 grid. The player who succeeds in placing three of their marks in a horizontal, vertical row wins the game.

### Getting started
To build a project, enter the project directory and use the `./gradlew assemble` command or use "Import Project" in Android Studio.
- Use `./gradlew connectedAndroidTest` to run the tests on a connected device.

### Requirements
- buildToolsVersion `31.0.0`
- compileSdk `31` - Android version 12
- minSdk `21` - Android version 5

### Architecture
My project architecture is based on the "Android Architecture Components" from Google, to provide a cleaner code, with clear separation between the view, the data and the business logic. <br>
Data is observed using Kotlin Flows and the DataBinding Library binds UI components in layouts to the DataSources ( Players and Games ).


### Built With
- `Kotlin` - official programming language for Android development 
- `Coroutines` - for background operations.
- `Room` - Persistence of Data.
- `Hilt` - Dependency Injection Framework.
- `Navigation component` - to manage fragment operations.
- `Material` -  for the user interface

## Project structure

| Path | Description |
|:----:|:-----------:|
| `buildsrc/src/main/java/` | Configs and dependencies of the project|
| `app/src/androidTest/java/` | All tests of the project|
| `app/src/main/java/data/source` | All entities, dataSources of the project|
| `app/src/main/java/data/repository` | All repo implemented to get access to the data|
| `app/src/main/java/di` | modules - Dependency Injection|
| `app/src/main/java/domain` | models and repositories entry|
| `app/src/main/java/ui` | User Interface of the project


### Screenshots
Screen of NewGame
![alt text](https://github.com/2021-DEV2-053/MyTest/blob/main/resources/device-2021-09-24-155523.png?raw=true)
Screen of InGame
And then Enjoy it !
![alt text](https://github.com/2021-DEV2-053/MyTest/blob/main/resources/device-2021-09-24-155757.png?raw=true)