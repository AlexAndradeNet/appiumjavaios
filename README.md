# Appium + Java + Android - 2023

This project uses the following technologies:
* Java 17
* Appium
* Serenity BDD
* Cucumber
* Gradle
* Android 13
* Android SDK
* IntelliJ IDEA

If you need help to install the Android SDK, you can find the instructions at the end of this file.

And it's written with the design pattern Screenplay that helps to maintain the SOLID principles.

This project is based on https://github.com/Jacobvu84/serenity-screenplay-appium/

---
## Running the project

1. Change de device name in the file `/src/test/resources/serenity.conf`
2. Run the emulator: `emulator -avd Pixel_5 -no-boot-anim -no-audio`
3. Run the Appium server: `appium --allow-insecure --allow-cors --session-override`
4. Run the tests. See the two next sections for more details.

### Running from Gradle / Terminal

1. Open a terminal
2. Navigate to the project root
3. Run the following command: `./gradlew clean test aggregate`

### Running feature from IntelliJ

1. Click on the `feature` file you want to run
2. In the Run menu Select Run...
3. In the contextual menu, select the feature, then "Edit..."
4. You should now see the 'Edit Configuration Settings' window. Set the main class to `net.serenitybdd.cucumber.cli.Main`
5. Change the Glue field to the root package of your project (or of your step definitions)
6. Click Apply

Source: https://johnfergusonsmart.com/running-cucumber-serenity-feature-files-directly-intellij/

---
## Reporting

The reports are generated in the `target/site/serenity` folder.

If you are in Mac, you can run the following command to open the report in your browser:
```bash
open target/site/serenity/index.html
```

If you are in Windows, you can run the following command to open the report in your browser:
```powershell
start target/site/serenity/index.html
```

---
## Linting

### Java

The Java files are linted using the `checkstyle` tool. The configuration file is located in the `checkstyle.xml` file.
Run the following command to lint the Java files:
```bash
# To check the code
./gradlew clean build spotlessCheck
# To fix the code
./gradlew clean build spotlessApply
```

### Linting Gherkin files

The Gherkin files are linted using the `gherkin-lint` tool. The configuration file is located in the `.gherkin-lintrc` file.
Run the following command to lint the Gherkin files:
```bash
sh gherkin_check.sh
```

---
## Troubleshooting

### Error: Appium cannot locate the device

The device can have a different name than the one you are using in the code. You can check the name of the device by running the following command:
```bash
adb devices
```
The result is similar to this:
```
List of devices attached
emulator-5554	device
```
In this case, the device name is `emulator-5554`. You need to change the device name in the file `/src/test/resources/serenity.conf`.

### GitHub delete the APK file

GitHub deletes the APK file after 100 days. You can download the APK file from the following link: https://apkfun.com/MetaTrader-5.html

---
## Installing Android Tools

These instructions are for Mac OS X. If you are using Windows, you can find the instructions here: https://developer.android.com/studio/install
These instructions install the minimal tools needed to run the tests. If you want to install the full Android Studio, you can find the instructions here: https://developer.android.com/studio/install

```bash
brew update
brew install openjdk@17
brew cask install android-commandlinetools
echo "export ANDROID_SDK_ROOT=/opt/homebrew/share/android-commandlinetools/" >> ~/.zprofile
source ~/.zprofile
```
If you are in Intel based Mac, you need change the `arm64-v8a` to `x86_64` in the following command:
```bash
sdkmanager "build-tools;33.0.0" "platform-tools" "emulator" "system-images;android-33;google_apis;arm64-v8a" "platforms;android-33"

# verify everything got installed
sdkmanager --list_installed
avdmanager create avd -n "Pixel_5" -d "pixel_5" -k "system-images;android-33;google_apis;arm64-v8a"
emulator -list-avds
emulator -avd Pixel_5
```
