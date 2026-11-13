# Versions

This is the branch `compat/agp-7.4`: AGP 7.4.2, Gradle 7.5, `compileSdk` and `targetSdk` 33, `fragment` 1.6.2, `material` 1.9.0, `activity` 1.7.2. `EdgeToEdge` is removed from `app`. The root `build.gradle.kts` pins `kotlin-stdlib-jdk7` and `jdk8` to 1.8.10. The tables below describe `master` (AGP 8.3.1, Gradle 8.4, `compileSdk` 34). Everything else is the same.

## Master

| Item | Version | Where |
|---|---|---|
| Android Gradle plugin | 8.3.1 | `gradle/libs.versions.toml`, key `agp` |
| Gradle | 8.4 | `gradle/wrapper/gradle-wrapper.properties` |
| JDK for Gradle | 17 | Gradle JDK in the IDE, or `JAVA_HOME` |
| `compileSdk`, `targetSdk` | 34 | `*/build.gradle.kts` |
| `minSdk` | 26 | `*/build.gradle.kts` |
| Java source level | 8 | `*/build.gradle.kts` |
| `androidx.fragment:fragment` | 1.8.5 | `libs.versions.toml`, key `fragment` |
| `androidx.lifecycle` (`viewmodel`, `livedata`) | 2.6.2 | key `lifecycle` |
| `com.google.android.material` | 1.10.0 | key `material` |
| `androidx.appcompat` | 1.6.1 | key `appcompat` |
| `androidx.recyclerview` | 1.3.1 | key `recyclerview` |
| `androidx.activity` | 1.8.0 | key `activity` |

Gradle 8.4 runs on JDK 8 to 20. A newer JDK (for example 25) gives "Gradle version 8.4 is incompatible with the Gradle JVM". Select JDK 17 in the IDE.

## Why `fragment` 1.8.5

- Versions 1.7.0 to 1.9.1 have `minCompileSdk` 34. `compileSdk` 33 needs `fragment` 1.6.2.
- Version 1.9.x has `minAndroidGradlePluginVersion` 8.1.1: no reserve for the AGP 8.1.1 target. Not used.
- `appcompat` 1.6.1 and `material` 1.10.0 bring older `fragment` versions. The explicit dependency raises them to 1.8.5.

## Branches

The branches are made from master by hand. Only the listed files change.

| Branch | Changes |
|---|---|
| `compat/agp-8.1.1` | `agp` = 8.1.1. Wrapper: Gradle 8.0 |
| `compat/agp-7.4` | `agp` = 7.4.2. Wrapper: Gradle 7.5. `compileSdk` and `targetSdk` = 33 in every module. `fragment` = 1.6.2, `material` = 1.9.0, `activity` = 1.7.2. In `app`: remove `EdgeToEdge` from `MainActivity`. In the root `build.gradle.kts`: pin `kotlin-stdlib-jdk7` and `kotlin-stdlib-jdk8` to 1.8.10 (as in `android-lists`) |

The branches are not built yet.

## Not checked

- A build with AGP 8.1.1 and `fragment` 1.8.5.
- A build of the branch `compat/agp-7.4` with `fragment` 1.6.2.
- A run on an emulator in the classroom.
