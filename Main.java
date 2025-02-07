package open.app;

import android.os.Bundle;
import android.util.Log;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import android.os.ServiceManager;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.ComponentName;
// import android.content.IIntentReceiver;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemClock;
import android.util.AndroidException;

public class Main {
public static final String LOG_TAG = "mytag";
String tag = "mytag";

void log(String s) {
	System.out.println(s);
}

int test = 0;
void test() { test++;}

/*
void bat(){
	// BatteryManager bm = (BatteryManager) getSystemService(Context.BATTERY_SERVICE);
	BatteryManager bm = (BatteryManager)
		ServiceManager.getService(
            Context.BATTERY_SERVICE);

	Intent bs = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
	double l = bs.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
	double s = bs.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
	double p = (l * 100.0) / s;
	Log.e(tag, "battery "+l+" "+s+" "+p);
	Log.e(tag, String.format("battery %f, %f, %f",l,s,p));
}
*/

void usm(){

// IUsageStatsManager usm = IUsageStatsManager.Stub.asInterface(ServiceManager.getService(
//             Context.USAGE_STATS_SERVICE));

		ServiceManager.getService(
            Context.USAGE_STATS_SERVICE);

}

void context(){
	Intent intent = new Intent(InstrumentationRegistry.getContext(), TestComponentsService.class);
}

public Main(){
	// log("");
	// bat();
	context();
}

public static void main(String[] args) {
	new Main();
}

}
