
package open.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.util.Log;
import android.widget.TextView;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import open.app.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {
	private ActivityMainBinding binding;
	String tag = "mytag";
	private static final Logger L = Logger.getLogger(MainActivity.class.getName());

	void log() {
		L.warning("123abc java utilities log");
		Log.e(tag, "123abc android utilities log");
	}


void listen(){
	BroadcastReceiver myReceiver;
	myReceiver = new MyReceiver();

	// IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
		IntentFilter filter = new IntentFilter();
// filter.addAction(IntentActions.ACTION_IDLE_MAINTENANCE);
// android.intent.action.TIME_TICK
filter.addAction(Intent.ACTION_TIME_CHANGED);
filter.addAction(Intent.ACTION_TIME_TICK);
filter.addAction(Intent.ACTION_SCREEN_OFF);
filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
// filter.addAction(Intent.ACTION_DEVICE_IDLE_MODE_CHANGED);
filter.addAction(PowerManager.ACTION_DEVICE_IDLE_MODE_CHANGED);

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
void schedule() {
	ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
	Handler handler = new Handler(Looper.getMainLooper());

// ScheduledFuture<?> s =
s =
	// executor.scheduleAtFixedRate(myRunnable, delay, interval, unit);
	service.scheduleAtFixedRate(() -> {
	// service.schedule(() -> {
		handler.post(() -> {
			// Do your stuff here, It gets loop every 15 Minutes
			Log.e(tag, "tick");
			PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
			pm.isInteractive();
		});
	},
	0,
	// 15
	1,
	// TimeUnit.MINUTES
	TimeUnit.SECONDS
	);

// future.cancel(false); // Use 'false' to not interrupt a potentially running task
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
			Log.d(tag, "onCreate");
	super.onCreate(savedInstanceState);

	log();
	listen();
	// schedule();
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

@Override
public void onResume() {
    super.onResume();
    Log.w(tag, "onResume");
    // c.cancel();
}

}
