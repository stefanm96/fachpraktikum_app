# Fachpraktikum WI 2022

## GMAF Config Android App

## How to install:

1. install Android Studio
2. clone and import the project to Android Studio
4. run on emulator or on android phone

## How to run E2E tests:

1. Set up your test environment
2. To avoid flakiness, it is highly recommend that you turn off system animations on the virtual or
   physical devices used for testing. On your device, under:
   Settings > Developer options, disable the following 3 settings:
    1. Window animation scale
    2. Transition animation scale
    3. Animator duration scale
4. run ./gradlew connectedAndroidTest