#!/bin/env bash
set -x
set -e
apk=app/build/outputs/apk/debug/*.apk
chmod 644 $apk 
gradle assembleDebug |& ack -i error -A3 -m1 && false
chmod 400 $apk 
CLASSPATH=$apk app_process -Xnoimage-dex2oat / open.app.Main
