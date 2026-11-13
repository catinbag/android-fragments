# Android fragments

One topic, shown in several small apps: activities and fragments. The data is a catalog of movies (title, year, genre, rating, description). Each module is an app. There are no Navigation component and no network.

## Modules

| Module | What it shows |
|---|---|
| `core` | Pure Java. `Movie`, `Genre`, in-memory repository, `Movies.sample()` (20 movies), JUnit tests |
| `fr-basics` | First fragment, static `FragmentContainerView`, a log of all lifecycle callbacks (`Static`, `Lifecycle`) |
| `fr-transactions` | `add`, `replace`, back stack, arguments, `newInstance`. Three error modes with a menu switch |
| `fr-communication` | Three ways to connect fragments: listener interface, shared `ViewModel` + `LiveData`, Fragment Result API with a `BottomSheetDialogFragment`. One error mode |
| `adaptive` | The same fragments in one pane (portrait) and two panes (landscape) |
| `app` | An empty module made by the IDE project wizard. The demos do not use it |

## Find a demo

| Module | Screen | Entry class | Logcat tag |
|---|---|---|---|
| `fr-basics` | start menu | `MenuActivity` | none |
| `fr-basics` | `Static` | `StaticActivity`, `MovieListFragment` | none |
| `fr-basics` | `Lifecycle` | `LifecycleActivity`, `LoggingFragment` | `FragmentLifecycle` |
| `fr-transactions` | one screen | `MainActivity` | none |
| `fr-communication` | start menu | `MenuActivity` | none |
| `fr-communication` | `Interface` | `iface/InterfaceActivity` | `InterfaceDemo` |
| `fr-communication` | `ViewModel` | `viewmodel/ViewModelActivity` | `ViewModelDemo` |
| `fr-communication` | rating sheet (from details on both screens) | `rating/RatingBottomSheet` | none |
| `adaptive` | one screen | `MainActivity` | none |

`fr-communication` has two classes with the same name in two packages: `iface/MovieListFragment` and `viewmodel/MovieListFragment` (the same for `MovieDetailsFragment`). Each one shows one way to connect fragments.

## Error modes

A mode is a checkable item in the options menu (three dots). Checked means the bug is on. All modes are off by default: the code is correct. The value is kept in `SharedPreferences` and survives rotation.

| Module | Menu item | What happens |
|---|---|---|
| `fr-transactions` | `Bug: no savedInstanceState check` | After rotation the list is added again. The subtitle shows `Fragments: 2`, then `3`. After the mode is switched off, restart the app (swipe it away): the duplicates stay in the saved state |
| `fr-transactions` | `Bug: constructor with parameter` | Details open. After rotation the app crashes (`Fragment$InstantiationException`) |
| `fr-transactions` | `Bug: call after commit() (one shot)` | The next cold start crashes with a `NullPointerException`. The mode switches itself off before the crash |
| `fr-communication` (`ViewModel`) | `Bug: observe(this)` | After a return from the details, one selection writes two lines `list observer called` |

The subtitle of `fr-transactions` shows `Fragments: N`: how many fragments the `FragmentManager` holds. A list in the back stack is not counted.

## Run

Needs JDK 17 (Gradle JDK in the IDE) and Android SDK 34. Open the project folder in Android Studio and select a module in the run configuration. Or from the command line:

```
./gradlew :fr-transactions:installDebug
```

Replace `fr-transactions` with `fr-basics`, `fr-communication` or `adaptive`. Use a phone emulator. Rotate the screen to see the layouts of `adaptive` and the error modes.

## Test

```
./gradlew :core:test
```

The screens have no UI tests.

## Requirements

- JDK 17
- Android SDK 34
- The Gradle wrapper in this repository (Gradle 8.0). Android Gradle plugin 8.1.1. Library versions are in `gradle/libs.versions.toml`. Notes about versions: `docs/versions.md`.
