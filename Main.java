package open.app;

import android.os.Bundle;
import android.util.Log;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Looper;

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

import android.media.AudioManager;
import android.os.BatteryManager;

// import androidx.test.InstrumentationRegistry;
import java.lang.reflect.Method;

public class Main {
public static final String LOG_TAG = "mytag";
String tag = "mytag";

void log(String s) {
	System.out.println(s);
}

int test = 0;
void test() { test++;}

public static Context getSystemContext() {
	try {
		Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
		Method method = activityThreadClass.getMethod("systemMain");
		Looper.prepareMainLooper();
		Object activityThread = method.invoke(null);
		Method getSystemContextMethod = activityThreadClass.getMethod("getSystemContext");
		return (Context) getSystemContextMethod.invoke(activityThread);
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
}

/*
*/
void bat(){
	Context c = FakeContext.get();
	// Context c = getSystemContext();
	BatteryManager bm = (BatteryManager) 
	c.getSystemService(Context.BATTERY_SERVICE);
		// ServiceManager.getService(Context.BATTERY_SERVICE);

	Intent bs = c.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
	double l = bs.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
	double s = bs.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
	double p = (l * 100.0) / s;
	Log.e(tag, "battery "+l+" "+s+" "+p);
	Log.e(tag, String.format("battery %f, %f, %f",l,s,p));
}

void usm(){

// IUsageStatsManager usm = IUsageStatsManager.Stub.asInterface(ServiceManager.getService(
//             Context.USAGE_STATS_SERVICE));

		ServiceManager.getService(
            Context.USAGE_STATS_SERVICE);

}

void audio(){
	Workarounds.apply(true, true, true);
	Context c = FakeContext.get();
	// Context c = getSystemContext();
	final AudioManager am = (AudioManager)c.getSystemService(Context.AUDIO_SERVICE);
	am.getStreamVolume(0);
}

void context(){
	// Intent intent = new Intent(InstrumentationRegistry.getContext(), TestComponentsService.class);
	// Workarounds.apply(true, true, true);
	// Context c = FakeContext.get();
	Context c = getSystemContext();
	log(c.getPackageName());
	c.getApplicationContext();
	c.getSystemService(Context.BATTERY_SERVICE);
	final ContentResolver contentResolver = c.getContentResolver();
}

public Main(){
	log("abc");
	// context();
	// bat();
	audio();
}

public static void main(String[] args) {
	new Main();
}

}
