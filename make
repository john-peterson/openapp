#!/bin/env bash
# set -x
set -e
# tools_version=26.0.1
BUILD_TOOLS_VERSION=current
BUILD_TOOLS=$ANDROID_SDK/build-tools/$BUILD_TOOLS_VERSION
ANDROID_JAR=$ANDROID_SDK/platforms/android-33/android.jar
# androidx=~/.gradle/caches/transforms-3/27d576167a170fa332f195a5e220334e/transformed/appcompat-1.6.1-api.jar
jar=obj:$ANDROID_JAR:$androidx
# res=app/src/main/res
# src=app/src/main/java
src=.
# manifest=app/src/main/AndroidManifest.xml
manifest=AndroidManifest.xml
$BUILD_TOOLS/aapt package -f -m -J $src -M $manifest -I $ANDROID_JAR
jdk=7

rm -rf obj/*
# javac -d ./obj -source 1.7 -target 1.7 -bootclasspath $JAVA_HOME/jre/lib/rt.jar -classpath $ANDROID_JAR:obj -sourcepath src src/org/linaro/glmark2/*.java
# javac -Xmaxerrs 1 -d ./obj -source 1.7 -target 1.7 -bootclasspath $JAVA_HOME/jre/lib/rt.jar -classpath $jar -sourcepath $src  $src/open/app/*.java $src/com/android/internal/app/*.java
# find app/src -name "*.java" | xargs javac -Xmaxerrs 1 -d ./obj -source 1.7 -target 1.7 -bootclasspath $JAVA_HOME/jre/lib/rt.jar -classpath $jar -sourcepath  
# javac -Xmaxerrs 1 -Xlint:-options -d ./obj -source 1.7 -target 1.7 -bootclasspath $JAVA_HOME/jre/lib/rt.jar -classpath $jar -sourcepath $src $src/Main.java
# javac -Xmaxerrs 1 -Xlint:-options -target $jdk -source $jdk -classpath $jar -d obj Main.java ServiceManager.java 
# javac -Xmaxerrs 1 -Xlint:-options -target $jdk -source $jdk -classpath $jar -d obj Main.java FakeContext.java ServiceManager.java Workarounds.java
javac -Xmaxerrs 1 -Xlint:-options -target $jdk -source $jdk -classpath $jar -d obj *.java

$BUILD_TOOLS/dx --dex --output=out/classes.dex ./obj
$BUILD_TOOLS/aapt package -f -M $manifest -I $ANDROID_JAR -F unaligned.apk out
# rm app/src/main/java/open/app/R.java
chmod 400 unaligned.apk
CLASSPATH=unaligned.apk app_process -Xnoimage-dex2oat / open.app.Main -help
