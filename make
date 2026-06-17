#!/bin/env bash
set -x
apk=(app/build/outputs/apk/debug/*.apk)
chmod 644 $apk 
set -e
gradle assembleDebug |& ack -i error -A3 -m1 && false
set +e
chmod 400 $apk 
# CLASSPATH=$apk app_process -Xnoimage-dex2oat / open.app.Main
CLASSPATH=$apk app_process -Xnoimage-dex2oat / open.app.main
chmod 644 $apk 
