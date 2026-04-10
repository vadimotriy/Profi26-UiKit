1.  Cmd + Shift + A 
2.  Execute Gradle Task
3.  assembleRelease + :uikit
4.  uikit/build/outputs/aar/uikit-release.aar

Дальше засунуть его в app/libs
build.gradle (:app) -> implementation(files("libs/uikit-release.aar"))