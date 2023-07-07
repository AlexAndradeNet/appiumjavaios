# Appium + Java + iOS - 2023

This project uses the following technologies:

* ⚠️ **MacOS. This does not work in Windows, Linux u other OS.** ⚠️
* Java 17
* Appium 2
* Serenity BDD
* Cucumber
* Gradle
* Xcode
* IntelliJ IDEA

If you need help to install the environment, you can find the instructions in
this file [INSTALLATION.md](INSTALLATION.md).

And it's written with the design pattern Screenplay that helps to maintain the
SOLID principles.

This project is based
on https://github.com/Jacobvu84/serenity-screenplay-appium/

---

## 🚀 Running the project

1. Change de device name in the file `/src/test/resources/serenity.conf`
2. Run the emulator (See [INSTALLATION.md](INSTALLATION.md) for more details):

```bash
/Applications/Xcode.app/Contents/Developer/Applications/Simulator.app/Contents/MacOS/Simulator -CurrentDeviceUDID <YOUR-DEVICE-ID>
```

3. Run the Appium server:

```bash
appium --allow-insecure true --allow-cors --session-override
```

4. Run the tests. See the two next sections for more details.

### Running from Gradle / Terminal

1. Open a terminal
2. Navigate to the project root
3. Run the following command: `./gradlew clean test aggregate`

### Running feature from IntelliJ

1. Click on the `feature` file you want to run
2. In the Run menu Select Run...
3. In the contextual menu, select the feature, then "Edit..."
4. You should now see the 'Edit Configuration Settings' window. Set the main
   class to `net.serenitybdd.cucumber.cli.Main`
5. Change the Glue field to the root package of your project (or of your step
   definitions)
6. Click Apply

Source: https://johnfergusonsmart.com/running-cucumber-serenity-feature-files-directly-intellij/

---

## 📊 Reporting

The reports are generated in the `target/site/serenity` folder.

If you are in Mac, you can run the following command to open the report in your
browser:

```bash
open target/site/serenity/index.html
```

---

## 💅🏽 Linting

> The ratio of time spent reading versus writing is well over 10 to 1. We are
> constantly reading old code as part of the effort to write new code. …making
> it
> easy to read makes it easier to write.

_Robert C. Martin (a.k.a Uncle Bob)_

### Java

The Java files are linted using the `checkstyle` tool. The configuration file is
located in the `checkstyle.xml` file.
Run the following command to lint the Java files:

```bash
# To check the code
./gradlew clean build spotlessCheck
# To fix the code
./gradlew clean build spotlessApply
```

### Linting Gherkin files

The Gherkin files are linted using the NodeJS `gherkin-lint` tool. The
configuration
file is located in the `.gherkin-lintrc` file, and helps you find duplicated
test names that can cause problems in the reports.
Run the following command to lint the Gherkin files:

```bash
sh gherkin_check.sh
```

---

## 🐞 Troubleshooting

### GitHub delete the App file

GitHub deletes the APK/IPA file after 100 days. You can download the APK/IPA
file from the following
link: https://github.com/bitbar/test-samples/tree/master/apps
