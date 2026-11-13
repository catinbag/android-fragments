# Checks by running

Each check needs an emulator or a device. Write the result in the last column.

| № | Check | How | Result |
|---|---|---|---|
| 1 | Does the fragment have a View after `commitNow()` in `onCreate` of the Activity? | In `fr-transactions` `MainActivity.onCreate`, replace `commit()` with `commitNow()` and log `list.getView()` right after it. Expected: null until `onStart` of the Activity | |
| 2 | Text of the exception for `inflate(..., container, true)` with `FragmentContainerView` | In a fragment, override `onCreateView` and inflate with `attachToRoot` = true | |
| 3 | In which version `setUserVisibleHint` is deprecated: 1.1.0 or 1.2.0 | Read the Javadoc or the release notes of `androidx.fragment` | |
| 4 | Does a waiting Fragment Result survive the death of the process? | `fr-communication`: open the sheet, send the app to the background, run `adb shell am kill org.demo.fragments.frcommunication` (or use "Don't keep activities"), return, choose a rating | |
| 5 | Name of the lint check `FragmentLiveDataObserve`. Does it fire in Java? | `fr-communication`, `viewmodel/MovieListFragment`: `observe(this, observer)`. Run `./gradlew :fr-communication:lintDebug` and read the report | |
| 6 | Order of callbacks of the Activity and the fragment | `fr-basics`, screen `Lifecycle`. Filter `tag:FragmentLifecycle`. Start, rotate, `Details`, back | |
| 7 | Does lint mark `BrokenDetailsFragment` (`ValidFragment`)? | Open the file in the IDE. Run `./gradlew :fr-transactions:lintDebug` | |
| 8 | Text of `Fragment$InstantiationException` and of "No view found for id" | `fr-transactions`: mode `constructor with parameter`, open details, rotate. For the second text: in `adaptive`, remove `adaptRestoredDetails()` and rotate with the details open | |
| 9 | A duplicate of fragments after rotation with `add`. Two lines of the log with `observe(this)` | `fr-transactions`: mode `no savedInstanceState check`, rotate twice (subtitle). `fr-communication`: mode `observe(this)`, select a movie, back, select another one, filter `tag:ViewModelDemo` | |

## Extra checks

| № | Check | How | Result |
|---|---|---|---|
| 10 | `adaptive`: rotation with the details open, both directions | Portrait, select a movie, rotate. Then rotate back. Expected: the same movie, no crash | |
| 11 | `adaptive`: rotation with the list only | Rotate in both directions without a selection. Expected: no crash | |
| 12 | `fr-communication`: the sheet after rotation | Open the sheet, rotate, choose 4 stars. Expected: the sheet is restored, `My rating: 4` | |
| 13 | `fr-transactions`: call after `commit()` | Switch the mode on, swipe the app away, open it. Expected: `NullPointerException`. The next start works | |
| 14 | `fr-communication` (`ViewModel`): rotation with the list after Back | Select a movie, back, rotate. Expected: the list stays, the details do not open again | |
