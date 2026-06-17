#!/bin/env bash
# set -x
set -e
# tools_version=26.0.1
BUILD_TOOLS_VERSION=current
BUILD_TOOLS=$ANDROID_SDK/build-tools/$BUILD_TOOLS_VERSION
ANDROID_JAR=$ANDROID_SDK/platforms/android-33/android.jar
# androidx=~/.gradle/caches/transforms-3/27d576167a170fa332f195a5e220334e/transformed/appcompat-1.6.1-api.jar
jar=obj:$ANDROID_JAR:$androidx
src=.
manifest=AndroidManifest.xml
$BUILD_TOOLS/aapt package -f -m -J $src -M $manifest -I $ANDROID_JAR
# jdk=1.7
jdk=7
# jdk=17

rm -rf obj/*
# javac -Xmaxerrs 1  -d ./obj -source $jdk -target $jdk -classpath $jar  -sourcepath $src Main.java
# javac -Xmaxerrs 1 -Xlint:-options -d obj -source $jdk -target $jdk -classpath $jar  -sourcepath $src Main.java
javac -Xmaxerrs 1 -Xlint:-options -d obj -source $jdk -target $jdk -classpath $jar  Main.java main.java
# javac -Xmaxerrs 1 -Xlint:-options -d ./obj -source 1.7 -target 1.7 -bootclasspath $JAVA_HOME/jre/lib/rt.jar -classpath $jar -sourcepath $src $src/Main.java
# find app/src -name "*.java" | xargs javac -Xmaxerrs 1 -d ./obj -source 1.7 -target 1.7 -bootclasspath $JAVA_HOME/jre/lib/rt.jar -classpath $jar -sourcepath  
# javac -Xmaxerrs 1 -d ./obj -source 1.7 -target 1.7 -bootclasspath $JAVA_HOME/jre/lib/rt.jar -classpath $jar -sourcepath $src $src/open/app/Main.java

$BUILD_TOOLS/dx --dex --output=out/classes.dex ./obj
$BUILD_TOOLS/aapt package -f -M $manifest -I $ANDROID_JAR -F unaligned.apk out
chmod 400 unaligned.apk
# CLASSPATH=unaligned.apk app_process -Xnoimage-dex2oat / open.app.Main -help
CLASSPATH=unaligned.apk app_process -Xnoimage-dex2oat / open.app.main -help
# adb install unaligned.apk
# cmd package install unaligned.apk
