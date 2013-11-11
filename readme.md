## Setup

### Phantom.js

This component emulates the browser. Download and install using the instructions at http://phantomjs.org/download.html

### Java

JDK 7 is required to execute the tests. Download and install from http://java.oracle.com

### Gradle

This is the build tool that drives the tests. Download and install using the instructions at http://www.gradle.org/downloads

### IntelliJ

For now, tests can only be run via intellij (until Gradle issues are fixed). Download and install from
http://confluence.jetbrains.com/display/IDEADEV/IDEA+13+EAP (community edition should be fine).

To open the project in intellij, choose `import project` and select the file `build.gradle`. It should take some time
to download dependencies and then it will be ready.

## Configuration

### .env

Create the file `.env` in the base of the project (same level as `build.gradle`). Edit it to include:

    # The base URL for your JomSocial instance
    BASE_URL = http://localhost/jomsocial

    # The path to your installation of phantomjs
    PHANTOMJS_BIN = C:/Tools/phantomjs-1.9.2-windows/phantomjs.exe

## Testing

For now there are problems with running the test via Gradle. Run the test in IntelliJ instead. First make sure JomSocial
is running and then open the test class `ActivityLikingTest` and press `SHIFT``F10`.

