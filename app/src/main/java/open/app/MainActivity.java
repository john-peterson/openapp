
package open.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import java.util.logging.Logger;

import android.hardware.display.DisplayManager;
import android.view.Display;
import android.os.PowerManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;

import open.app.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {
	private ActivityMainBinding binding;
	String tag = "mytag";
	private static final Logger L = Logger.getLogger(MainActivity.class.getName());
	PowerManager pm;

	void log() {
		L.warning("123abc java utilities log");
		Log.e(tag, "123abc android utilities log");
	}

	void service(){
Intent serviceIntent = new Intent(MainActivity.this, MyService.class);
// startService(serviceIntent);
startForegroundService(serviceIntent);

// Intent serviceIntent = new Intent(MainActivity.this, MyService.class);
// stopService(serviceIntent);

	}


void listen(){
	BroadcastReceiver myReceiver;
	myReceiver = new MyReceiver();

	// IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
		IntentFilter filter = new IntentFilter();
// filter.addAction(IntentActions.ACTION_IDLE_MAINTENANCE);
filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
// filter.addAction(Intent.ACTION_DEVICE_IDLE_MODE_CHANGED);
filter.addAction(PowerManager.ACTION_DEVICE_IDLE_MODE_CHANGED);
filter.addAction(PowerManager.ACTION_DEVICE_LIGHT_IDLE_MODE_CHANGED);
// filter.addAction(Intent.ACTION_TIME_CHANGED);
// filter.addAction(Intent.ACTION_TIME_TICK);
filter.addAction(Intent.ACTION_SCREEN_OFF);
filter.addAction(Intent.ACTION_SCREEN_ON);
// filter.addAction(Intent.USER_PRESENT);

	// Use ContextCompat.registerReceiver for compatibility with newer Android versions
	// and specify the exported state flag
	// int flags = ContextCompat.RECEIVER_NOT_EXPORTED; 
	// ContextCompat.
	// registerReceiver(this, myReceiver, filter, flags);
	// registerReceiver(myReceiver, filter, flags);
	registerReceiver(myReceiver, filter);

	// context.
		// registerReceiver(idleStateListener, IntentFilter(PowerManager.ACTION_DEVICE_IDLE_MODE_CHANGED));
		 

	// airplaneModeReceiver = new BroadcastReceiver() {
	// };

}


ScheduledFuture<?> s;
long idle = 0, cur=0,off = 0, tick=0;
// int era = 0;

DisplayManager dm;

// for (Display display : dm.getDisplays()) {
//     if (display.getState() != Display.STATE_OFF) {
//         return true;
//     }
// }

void sleep(){
// long a = System.currentTime();
long a = System.currentTimeMillis();
long b = 0;
while (b - a < 500	) {
		b = System.currentTimeMillis();
		}
}

void task() {
	Log.e(tag, "task 1 "+status());
	// dm.getDisplays()[0].getState() != Display.STATE_OFF) {
	// if(dm.getDisplays()[0].getState() == Display.STATE_ON) {

	long a = System.currentTimeMillis();
	try {Thread.sleep(500);} catch (Exception e) {}
	long b = System.currentTimeMillis();
	if (b-a > 1000) {
	try {Thread.sleep(500);} catch (Exception e) {}
	}

	// sleep();
	Log.e(tag, "task 2 "+status());
	if (pm.isInteractive())  {
		// if (!pm.isDeviceIdleMode())  {
		// if (!pm.isDeviceLightIdleMode())  {
		// idle = 0;
		off = System.currentTimeMillis();
		// Log.e(tag, "on ");
		return;
	}
	// }		else		{
	// idle++;
	// cur = System.currentTime();
	cur = System.currentTimeMillis();
	idle = cur - off;
	Log.e(tag, "idle "+idle);
	status();
	// while (!pm.isScreenoff()) {} 

	// if (idle > 10 * 1000) {
	// if (idle > 15 * 1000) {
	if (idle > 25 * 1000) {
		// if (idle > 5 * 60 * 1000) {
		s.cancel(false);
		finishAndRemoveTask();
	}
	// }
}

void task2() {
		cur = System.currentTimeMillis();
		long notick =  cur - tick;
		tick = System.currentTimeMillis();

	if (notick > 2 * 1000) {
		Log.e(tag, "last tick "+notick+" "+status());
		long on = System.currentTimeMillis();
		// Log.e(tag, "on "+idle);
		while (
				// !pm.isInteractive()
				dm.getDisplays()[0].getState() == Display.STATE_OFF
				) {} 
		cur = System.currentTimeMillis();
		long delay  = cur - on;
		Log.e(tag, "delay "+delay);
	}
}

void schedule() {
	ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
	Handler handler = new Handler(Looper.getMainLooper());
	// int idle = 0;
	tick = System.currentTimeMillis();

	// ScheduledFuture<?> s =
	s =
	// executor.scheduleAtFixedRate(myRunnable, delay, interval, unit);
	service.scheduleAtFixedRate(() -> {
	// service.schedule(() -> {
		handler.post(() -> {
			task();
			// task2();
		});
	},
	0,
	// 15
	1,
	// TimeUnit.MINUTES
	TimeUnit.SECONDS
	);

// s.cancel(false); // Use 'false' to not interrupt a potentially running task
}

/*
void alarm() {
alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
        SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HALF_HOUR,
        AlarmManager.INTERVAL_HALF_HOUR, alarmIntent);

//         // Need the original PendingIntent
//
Intent intent = new Intent(context, MyAlarmReceiver.class);
PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
alarmManager.cancel(pendingIntent);

}

void job() {
// For JobScheduler
JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
jobScheduler.cancel(jobId);

// For WorkManager
WorkManager.getInstance(context).cancelAllWorkByTag(yourTag);
// or
WorkManager.getInstance(context).cancelWorkById(yourWorkId);

}
*/

// Timer timer = new Timer();
// timer.schedule( new performClass(), 30000 );


CountDownTimer c; 

	void timer() {
			c =	new CountDownTimer(30 * 1000, 1  * 1000) {
			public void onFinish() {
				Log.e(tag, "done");
				// TextView myTextView = (TextView) findViewById(R.id.my_text_view);
				// myTextView.setText("New Text Set from timer");
				// this.finish();
				// finish();
				// finishAndRemoveTask();
				start();
			}
			public void onTick(long t) {
				Log.e(tag, "tick " + t/1000);
			}
		};
		// }.start();
		c.start();
		// c.cancel();
	}

private Handler handler = new Handler(Looper.getMainLooper());
private Runnable runnable;

private void startNoTickTimer() {
			Log.d(tag, "Timer start");
	runnable = new Runnable() {
		@Override
		public void run() {
			Log.d(tag, "Timer finished!");
		}
	};
	handler.postDelayed(runnable, 20 * 1000);
}

private void cancelTimer() {
	handler.removeCallbacks(runnable);
}

// @Override
// protected void onDestroy() {
//     super.onDestroy();
//     cancelTimer();
// }



// public static String create_folder(Context context) {
// 	File[] directory = new File[0];
// 	directory = context.getExternalMediaDirs();
// 	for(int i = 0;i<directory.length;i++){
// 		if(directory[i].getName().contains(context.getPackageName())){
// 			return directory[i].getAbsolutePath();
// 		}
// 	}
// 	return null;
// }

void gui() {
	TextView myTextView = (TextView) findViewById(R.id.my_text_view);
	myTextView.setText("New Text Set from Java");
}


@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	Log.d(tag, "onCreate");
	pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
	dm = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);

	log();
	// service();
	listen();
	schedule();
	// timer();
	// startNoTickTimer();

	binding = ActivityMainBinding.inflate(getLayoutInflater());
	setContentView(binding.getRoot());
	// setContentView(R.layout.activity_main); // Link the XML layout
	gui();

}

@Override
protected void onDestroy() {
	Log.d(tag, "onDestroy");
	super.onDestroy();
	this.binding = null;
}

@Override
public void onPause() {
    super.onPause();
    Log.e(tag, "onPause");
	// c.cancel();
	// c.start();
}

String status(){
	if (pm == null) return "";
	if (dm == null) return "";

	// Log.w(tag, "isDeviceIdleMode "+pm.isDeviceIdleMode());
	// Log.w(tag, "isDeviceLightIdleMode "+pm.isDeviceLightIdleMode());
	// Log.w(tag, "isInteractive "+pm.isInteractive());
	// Log.w(tag, "isScreenon "+pm.isScreenOn());

	// Log.w(tag, "getState "+ dm.getDisplays()[0].getState() + " on="+ (dm.getDisplays()[0].getState() == Display.STATE_ON));
	
		return String.format("idle=%b, interactive=%b, state=%d, on=%b",
			pm.isDeviceIdleMode(),
			pm.isInteractive(),
			dm.getDisplays()[0].getState(), 
			dm.getDisplays()[0].getState() == Display.STATE_ON
			);
}

@Override
public void onResume() {
	Log.w(tag, "onResume "+status());
	// while (!pm.isInteractive()) {} 
	// c.cancel();
	super.onResume();
}

}
