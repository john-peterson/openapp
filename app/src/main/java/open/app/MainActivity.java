// package mohammad.samandari.standup;
package open.app; 

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.time.Clock;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

static Context context;
private NotificationManager mNotificationManager;
ToggleButton alarmToggle;
Intent notifyIntent;
private PendingIntent notifyPendingIntent;
static private AlarmManager am;

private static  int NOTIFICATION_ID = 1;
private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
private static final String tag = "mytag";
private static final String TAG = tag;
String alarmId = "abc";

static void log(String s){
	Log.d(TAG, s);
}

void sleep(int s) {
	try {	Thread.sleep(s);} catch (Exception e) {}
}

Intent alarmIntent( ) {
	return alarmIntent("abc");
}

Intent alarmIntent(String action) {
	Intent	intent = new Intent(this, AlarmReceiver.class);
	intent.setAction(action);
	return intent;
}

int alarmId() {
	return alarmId.hashCode();
}

int alarmFlags() {
	// int flags = 
	return PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE ;
}

void setAlarm(){
	Intent i = alarmIntent();
	setAlarm(alarmId(), i, alarmFlags());
}

void setAlarm(int id, String action){
}

void setAlarm(int id, Intent intent, int flags ){
	log("set alarm "+id+" "+intent.getAction());
	//initialize mNotificationManager using getSystemService().
	// mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

	// alarmToggle = findViewById(R.id.alarmToggle);

	// createNotificaionChannel();

	// Intent	intent = new Intent(this, AlarmReceiver.class);
	// intent.setAction(action);
	// int flags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE ;
	// flags =  PendingIntent.FLAG_MUTABLE ;

	PendingIntent	pi = PendingIntent.getBroadcast(this, id, intent, flags); 


		////If the Toggle is turned on, set the repeating alarm with a 15 minute interval
		// long repeatInterval = AlarmManager.INTERVAL_HALF_HOUR;
	long repeatInterval = 1 * 1000; 
	// long repeatInterval = 30 * 1000; 
	long triggerTime = SystemClock.elapsedRealtime() + repeatInterval;
	// am.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, repeatInterval, notifyPendingIntent);
	am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, pi);

	//	   alarmManager.cancel(notifyPendingIntent);
	//	   mNotificationManager.cancelAll();

}

void cancelAlarm(){
	Intent i = alarmIntent();
	cancelAlarm(alarmId(), i, alarmFlags());
	// Intent	intent = new Intent(this, AlarmReceiver.class);
	// cancelAlarm(NOTIFICATION_ID, intent);
	// sleep(5000);
	// alarmUp();
	// delayed();
}

 void cancelAlarm(int id, String action ){
	Intent	intent = new Intent(this, AlarmReceiver.class);
	intent.setAction(action);
	cancelAlarm(id, intent, alarmFlags());
}

static void cancelAlarm(int id, Intent intent, int flags){
	try{
	log("cancel alarm "+id+" "+intent.getAction());
	// NOTIFICATION_ID=99;
	// int flags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE ;
	// int flags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE ;
	// int flags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE | PendingIntent.FLAG_NO_CREATE;
	// int flags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_NO_CREATE;
	PendingIntent pi = PendingIntent.getBroadcast(context, id, intent, flags); 
	am.cancel(pi);
	} catch (Exception e) {
		log(""+e);
	}
}

void alarmUp(){
	// Intent	intent = new Intent(this, AlarmReceiver.class);
	// intent.setAction("abc");
	Intent i = alarmIntent(); 
	int id = alarmId();
	boolean up = alarmUp(id, i);
	Log.d(TAG, "alarmUp "+id+" "+i.getAction()+ " = "+up );
}

void alarmUp(int id, String action){
	Intent	intent = new Intent(this, AlarmReceiver.class);
	intent.setAction(action);
	boolean up = alarmUp(id, intent);
	Log.d(TAG, "alarmUp "+id+" "+action+ " = "+up );
}

static boolean alarmUp(int id, Intent intent){
	// NOTIFICATION_ID=99;
	//To check if the alarm is on or not. and updating the toggle button.
	int flags = PendingIntent.FLAG_NO_CREATE|PendingIntent.FLAG_MUTABLE;
	// int flags = PendingIntent.FLAG_NO_CREATE|PendingIntent.FLAG_IMMUTABLE;
	// int flags = PendingIntent.FLAG_NO_CREATE;
	// boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent, flags) != null);
	PendingIntent alarmUp = PendingIntent.getBroadcast(context, id, intent, flags);
	// alarmToggle.setChecked(alarmUp);
	return alarmUp != null;
}

ScheduledFuture<?> s;
int i =0;
void schedule(){
	ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
	Handler handler = new Handler(Looper.getMainLooper());
	// int idle = 0;
	// tick = System.currentTimeMillis();

	Runnable task = new Runnable() {
		@Override
		public void run() {
			// log("Task executed at: " + System.currentTimeMillis());

			if (i == 0)
				alarmUp();
				// alarmUp(1, "abc");
			if (i == 1)
				setAlarm();
				// setAlarm(1, "abc");
			if (i == 2)
				alarmUp();
				// cancelAlarm(1, "abc");
			// 	alarmUp(1, "abc");
			// if (i == 3)
				// cancelAlarm();
				// alarmUp(1, "abc");
			if (i == 4)
				alarmUp();
			
			// if (i > 4)
				// alarmUp();
				// alarmUp(1, "abc");

			// timeoutTask();
			i++;
		}
	};

	// ScheduledFuture<?> s =
	s =
	// executor.scheduleAtFixedRate(myRunnable, delay, interval, unit);
	service.scheduleAtFixedRate(
	task,
	0,
	// 5,
	10,
	// 1,
	// TimeUnit.MINUTES
	TimeUnit.SECONDS
	);

// s.cancel(false); // Use 'false' to not interrupt a potentially running task
}

private void delayed() {
	// Log.d(tag, "Timer start");
	 Handler handler = new Handler(Looper.getMainLooper());
	 Runnable runnable = new Runnable() {
		@Override
		public void run() {
			// Log.d(tag, "Timer finished!");
			alarmUp(1, "abc");
			// cancelAlarm();
		}
	};
	handler.postDelayed(runnable, 5 * 1000);
}

@Override
protected void onCreate (Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	context = this;
	am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
	log("");
	Log.d(TAG, "onCreate: Started");

	// log(""+alarmIntent().getAction().hashCode());
	// log(""+alarmIntent().getAction().hashCode());

	// alarmUp();
	// setAlarm();
	// alarmUp();
	// cancelAlarm();
	// alarmUp();

	// alarmUp(1, "abc");
	// setAlarm(1, "abc");
	// alarmUp(1, "abc");
	// cancelAlarm(1, "abc");
	// alarmUp(1, "abc");

	// delayed();
	schedule();

}

/**
 * Creates a Notification channel, for OREO and higher.
 */
private void createNotificaionChannel () {
	Log.d(TAG, "createNotificaionChannel: MainActivity");
	if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
		// Create the NotificationChannel with all the parameters.
		NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "Stand Up Notification", NotificationManager.IMPORTANCE_DEFAULT);

		notificationChannel.enableLights(true);
		notificationChannel.setLightColor(Color.RED);
		notificationChannel.enableVibration(true);
		notificationChannel.setDescription("Notifies every 15 minutes to stand up and walk");

		mNotificationManager.createNotificationChannel(notificationChannel);
	}
}


}
