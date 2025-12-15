
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

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.SystemClock;
import android.provider.AlarmClock;

import open.app.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {
	String tag = "mytag";
	// static Context context = this;
	 Context context = this;
	static MainActivity activity;
	int alarmId = 9;
	private ActivityMainBinding binding;
	static PowerManager pm;
	static DisplayManager dm;
	static AlarmManager am;

// public static MainActivity get() {
// return this;
// }

void log() {
	log("");
	log("");
	log("NEW SESSION");
}

void log(String s) {
	Log.d(tag, s);
}

void startService(){
	// Intent i = new Intent(MainActivity.this, MyService.class);
	Intent i = new Intent();
	i.setClass(MainActivity.this, MyService.class);
	// startService(i);
	startForegroundService(i);
}

void stopService(){
	log("stop service ");
	Intent serviceIntent = new Intent(MainActivity.this, MyService.class);
	stopService(serviceIntent);
}

void serviceIntent(){
	log("sending  service intent");
	Intent intent = new Intent(context, MyService.class);
	intent.setAction("close");
	startService(intent);

	int requestCode = 0;
	int flags = PendingIntent.FLAG_IMMUTABLE;
	// PendingIntent pi = PendingIntent.getService(context, requestCode, intent, flags);
	// PendingIntent.getService(service.context, requestCode, intent, flags);
	// startService(pi);
}

void listen(){
	log("register receiver ");
	BroadcastReceiver myReceiver;
	myReceiver = new MyReceiver();

	// IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
	IntentFilter filter = new IntentFilter();
	// IntentFilter filter = new IntentFilter("MESSAGE_PROCESSED");

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

	
	// filter.addAction(Intent.ALARM_ALERT);
	// filter.addAction(AlarmManager.ALARM_ALERT);
	// filter.addCategory(Intent.CATEGORY_DEFAULT);

	// filter.addDataScheme("package");

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


static PendingIntent pi;
void setAlarm() {
	log("set alarm");
	// Context context = getContext():
	// Context context = getApplicationContext();
	Context context = this;

	Intent intent = new Intent();
	// Intent intent = new Intent(context, MyReceiver.class);
	// Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
	intent.setClass(context, MyReceiver.class);
	// intent.setAction(AlarmClock.ACTION_SET_ALARM);
	intent.setAction("alarm");
	// intent.putExtra(AlarmClock.EXTRA_HOUR, 11);
	// intent.putExtra(AlarmClock.EXTRA_MINUTES, 20);
	// intent.putExtra(AlarmClock.EXTRA_MESSAGE, "My custom alarm name"); // Set the name/label
	// startActivity(i);

	// Intent intent = new Intent(context, MyReceiver.class);
	// Intent serviceIntent = new Intent(MainActivity.this, MyService.class);
    // Intent intent = new Intent("MESSAGE_PROCESSED");
	
	// int flags = 0;
	// int flags = PendingIntent.FLAG_MUTABLE);
	int flags = PendingIntent.FLAG_MUTABLE;
// PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_MUTABLE);
	pi = PendingIntent.getBroadcast(context, alarmId, intent, flags);

    // am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1000, pi);
    // am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 20 * 1000, pi);
	// am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,	SystemClock.elapsedRealtime() +	10 * 1000, pi);
	// am.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
	
	am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000, pi);
	// am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 60 * 1000, pi);
	// am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,	SystemClock.elapsedRealtime() +	10 * 1000, pi);
	// am.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 10, pi);
}

int i=0;
// public static void cancel() {
public  void cancelAlarm() {
	try {
	log("cancel alarm ");
	// log(MyReceiver.intent(pi.intent));
	// am.cancel(pi);

	Intent intent = new Intent(this, MyReceiver.class);
	intent.setAction("alarm");
	// intent.setAction(AlarmClock.ACTION_SET_ALARM);
	PendingIntent pi2 = PendingIntent.getBroadcast(this , i, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	am.cancel(pi2);
	i++;
	} catch (Exception e) {
		log(""+e);
	}
}

/*
void alarm2() {
	private AlarmManager am;
	am.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
			SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HALF_HOUR,
			AlarmManager.INTERVAL_HALF_HOUR, pi);

	//         // Need the original PendingIntent
	//
	Intent intent = new Intent(context, MyAlarmReceiver.class);
	PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

	alarmManager.cancel(pendingIntent);
}
*/

ScheduledFuture<?> s;
long idle = 0, lastIdle = 0, cur=0,off = 0, tick=0;
// int era = 0;
boolean lock = false;

// for (Display display : dm.getDisplays()) {
//     if (display.getState() != Display.STATE_OFF) {
//         return true;
//     }
// }

void sleep(){
		try {Thread.sleep(10);} catch (Exception e) {}
}

void waitForWakeup(){
	long start = System.currentTimeMillis();
	long wait = 0;
	// while (c < 5000	) {
	// while (b-a > 1000	) {
	while(true){
		// c = b -a;
		sleep();
		long cur = System.currentTimeMillis();
		wait = cur - start;
		// start over
		if (wait > 400)
			start = System.currentTimeMillis();
		if (wait > 300)
			break;
	}
}

// void wait(){}

void timeoutTask(){
	if (lock) return;
	// while(lock) sleep();
	lock = true;
	timeoutTask2();
	lock = false;
}

void timeoutTask2() {
	// Log.e(tag, "task 1 "+status());
	// dm.getDisplays()[0].getState() != Display.STATE_OFF) {
	// if(dm.getDisplays()[0].getState() == Display.STATE_ON) {

	// waitForWakeup();
	// Log.e(tag, "task 2 "+status());

	if (pm.isInteractive())  {
		// if (!pm.isDeviceIdleMode())  {
		// if (!pm.isDeviceLightIdleMode())  {
		// idle = 0;
		off = System.currentTimeMillis()/1000;
		// Log.e(tag, "on ");
		return;
	}
	// }		else		{
	// idle++;
	// cur = System.currentTime();
	cur = System.currentTimeMillis()/1000;
	idle = cur - off;
	if (idle -lastIdle > 100)
		log("wake up window ");
	log(String.format("idle=%d, lastIdle=%d, {%s}", idle, lastIdle, status()));
	lastIdle = idle;

	// while (!pm.isScreenoff()) {}

	// if (idle > 10 * 1000) {
	// if (idle > 15 * 1000) {
	// if (idle > 25 * 1000) {
	// if (idle > 5 * 60 * 1000) {
	if (idle > 30 * 60) {
		log("task thread "+ Thread.currentThread().getName());
		finishAndRemoveTask();
	}
	if (idle > 60 * 60  ) {
		log("why are we still running ");
		s.cancel(false);
	}
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
	log("schedule");
	ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
	Handler handler = new Handler(Looper.getMainLooper());
	// int idle = 0;
	tick = System.currentTimeMillis();

	Runnable task = new Runnable() {
		@Override
		public void run() {
			log("Task executed at: " + System.currentTimeMillis());
			// timeoutTask();
			cancelAlarm();
		}
	};

	// ScheduledFuture<?> s =
	s =
	// executor.scheduleAtFixedRate(myRunnable, delay, interval, unit);
	service.scheduleAtFixedRate(
/*
			() -> {
	// service.schedule(() -> {
		handler.post(() -> {
			task();
			// task2();
		});
	},
	*/
	task,
	0,
	15,
	// 1,
	// TimeUnit.MINUTES
	TimeUnit.SECONDS
	);

// s.cancel(false); // Use 'false' to not interrupt a potentially running task
}


/*
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
			cancelAlarm();
			// stopService();
			// serviceIntent();
		}
	};
	handler.postDelayed(runnable, 10 * 1000);
}

private void cancelTimer() {
	handler.removeCallbacks(runnable);
}




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
protected void onDestroy() {
		log("onDestroy");
    super.onDestroy();
    // cancelTimer();
		this.binding = null;
}




static public String status(){
	if (pm == null) return "";
	if (dm == null) return "";

	// Log.w(tag, "isDeviceIdleMode "+pm.isDeviceIdleMode());
	// Log.w(tag, "isDeviceLightIdleMode "+pm.isDeviceLightIdleMode());
	// Log.w(tag, "isInteractive "+pm.isInteractive());
	// Log.w(tag, "isScreenon "+pm.isScreenOn());

	// Log.w(tag, "getState "+ dm.getDisplays()[0].getState() + " on="+ (dm.getDisplays()[0].getState() == Display.STATE_ON));

		return String.format("lightIdle=%b, idle=%b, interactive=%b, displayState=%d(1=off,2=on)",
			pm.isDeviceLightIdleMode(),
			pm.isDeviceIdleMode(),
			pm.isInteractive(),
			dm.getDisplays()[0].getState()
			// dm.getDisplays()[0].getState() == Display.STATE_ON
			);
}

static void closeApp(){
	// context.finishAndRemoveTask();
	if (activity == null) return;
	activity.finishAndRemoveTask();
}

@Override
public void onResume() {
	log("onResume");
	// Log.w(tag, "onResume "+status());
	// while (!pm.isInteractive()) {}
	// c.cancel();
	super.onResume();
}

@Override
public void onPause() {
	super.onPause();
	Log.e(tag, "onPause");
	// c.cancel();
	// c.start();
}

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	Log.d(tag, "onCreate");
	pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
	dm = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
	am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
	activity = this;

	log();
	// startService();
	// listen();
	// schedule();
	// timer();
	// startNoTickTimer();
	// setAlarm();

	binding = ActivityMainBinding.inflate(getLayoutInflater());
	setContentView(binding.getRoot());
	// setContentView(R.layout.activity_main); // Link the XML layout
	gui();

}

} //class
