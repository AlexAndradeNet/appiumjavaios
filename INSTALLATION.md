# IOS INSTALLATION

⚠️ **NOTE: This only works on Mac, since we need XCode to run the simulator.**
⚠️

We’ll need some dependencies to be preinstalled on your dev machine.

Let’s go over them one by one.

Also, remember it’s completely okay if you don’t understand all the details of
these in the beginning since Appium pretty much abstracts those details away,
and
you can always dig deeper later on if you need some very specific capabilities
of these libraries.

---

## ✏️ STEPS

### Install XCode IDE

To run iOS tests, we need a machine running macOS with Xcode installed. You
don't need it for automation, but it's necessary to run the simulator.

Go to the Mac Apple Store and install Xcode IDE.

This will take a lot of time to install, so be patient. You could continue with
the next steps while Xcode is installing.

### Install appium 2.x

If you installed Appium before with Homebrew, you might need to uninstall it.
Also, you might need to uninstall the appium-doctor and appium-desktop packages.

```bash
npm i -g appium@next
```

Install the webdriver

```bash
appium driver install xcuitest
```

### Install Carthage

You can think of Carthage as a tool to allow adding frameworks to your Cocoa
applications and to build required dependencies:

```bash
brew install carthage
```

### Install libimobiledevice

libimobiledevice library allows Appium to talk to iOS devices using native
protocols:

```bash
brew install libimobiledevice
```

### Install ios-deploy

ios-deploy helps to install and debug iOS apps from the command line:

```bash
brew install ios-deploy
```

### Install ios-webkit-debug-proxy

ios_webkit_debug_proxy (aka iwdp) proxies requests from usbmuxd daemon over a
web socket connection
Allows developers to send commands to MobileSafari and UIWebViews on real and
simulated iOS devices.

```bash
brew install ios-webkit-debug-proxy
```

### Install XCode Command Line Tools

⏰ **NOTE:** Wait until **XCode** installation (the first step) ends in order to
continue with the next steps.

The below command would set up Command-line scripts that are needed for us to be
able to run our first test:

```bash
xcode-select --install
```

### Boot the simulator

First decide what device you want to use:

```bash
xcrun simctl list
```

This will give you a list of devices:

```
...
== Devices ==
-- iOS 16.4 --
    iPhone SE (3rd generation) (447FDF42-DA89-4CF2-88F0-6F7A94402171) (Shutdown) 
    iPhone 14 (1312E380-F7E4-472A-B340-90F4B3866462) (Shutdown) 
    iPhone 14 Plus (67E9AA7E-2B7B-4DDE-98C5-785BF205B19C) (Shutdown) 
    iPhone 14 Pro (53DC7CBB-E475-4AAF-AEC0-6F45FABA7224) (Shutdown) 
    iPhone 14 Pro Max (5A1FE1CD-1B34-495D-98CA-5F168B3A05D5) (Shutdown) 
    iPad Air (5th generation) (B3A11ACA-6792-4800-9E34-C08675C4B8D1) (Shutdown) 
    iPad (10th generation) (8BBD7E5D-33D1-4953-99BF-52C625813B55) (Shutdown) 
    iPad mini (6th generation) (393124B0-E429-4DFE-BA21-6478696216BB) (Shutdown) 
    iPad Pro (11-inch) (4th generation) (4A35CB6C-7E20-4C85-B3B9-0DAFCF7881D7) (Shutdown) 
    iPad Pro (12.9-inch) (6th generation) (B413DADD-5A8F-451D-8958-763DB43D9FF6) (Shutdown) 
```

Choose the ID (eg. _447FDF42-DA89-4CF2-88F0-6F7A94402171_) you want (you can
create your own device using `xcrun simctl create` if you want).

Boot the simulator with that device (replacing YOUR-DEVICE-ID with the ID)

```bash
/Applications/Xcode.app/Contents/Developer/Applications/Simulator.app/Contents/MacOS/Simulator -CurrentDeviceUDID <YOUR-DEVICE-ID>
```

Now you should be able to use _simctl_ to install and launch commands.

```bash
xcrun simctl install <YOUR-DEVICE-ID> <PATH-TO-APPLICATION-BUNDLE>
xcrun simctl launch <YOUR-DEVICE-ID> <BUNDLE-ID-OF-APP-BUNDLE>
```

`xcrun simctl help` for more details. Note that booting a device using simctl
does not currently (Xcode 7.2) allow you to do anything else with that device
such as launch or install applications. You need to launch the device in the
simulator to actually do anything interesting. Also, you cannot delete a device
that is in use by the simulator, so you will have to quit/kill the simulator
before attempting to delete anything.

### Optional Dependencies

IDB (iOS Device bridge) is a node JS wrapper over IDB that are a set of
utilities made by Facebook:

```bash
brew tap facebook/fb
brew install idb-companion
pip3.6 install fb-idb
```

If you are curious, you could read the below reference blogs below that helped
me come up with this shortlist of dependencies and are good reads for more
context:

* [Real device setup](http://appium.io/docs/en/drivers/ios-xcuitest-real-devices/)
* [How to setup iOS Automation on Mac OS](https://www.youtube.com/watch?v=-_6C_-CMqSk)
  by Automation step by step
* [How To Test On Real iOS Devices With Appium, Part 1](https://appiumpro.com/editions/40-how-to-test-on-real-ios-devices-with-appium-part-1)
  from Appium pro

---

## 📗 BIBLIOGRAPHY

This is based on the
article [Writing Your First Appium Test For iOS Devices](https://applitools.com/blog/how-to-write-appium-ios-test/)
by Applitools
and [How can I launch the iOS Simulator from Terminal?](https://stackoverflow.com/questions/31179706/how-can-i-launch-the-ios-simulator-from-terminal)
in StackOverflow.
