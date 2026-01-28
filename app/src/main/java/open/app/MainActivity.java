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

public class MainActivity extends AppCompatActivity {

	private NotificationManager mNotificationManager;
	ToggleButton alarmToggle;
	Intent notifyIntent;
	private PendingIntent notifyPendingIntent;
	private AlarmManager alarmManager;


	private static final int NOTIFICATION_ID = 1;
	private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
	private static final String TAG = "mytag";


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(TAG, "onCreate: Started");

		//initialize mNotificationManager using getSystemService().
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		// alarmToggle = findViewById(R.id.alarmToggle);

		// createNotificaionChannel();

		notifyIntent = new Intent(this, AlarmReceiver.class);

		//To check if the alarm is on or not. and updating the toggle button.
		boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent,
				PendingIntent.FLAG_NO_CREATE|PendingIntent.FLAG_MUTABLE) != null);
		// alarmToggle.setChecked(alarmUp);
		Log.d(TAG, "alarmUp: "+alarmUp);

		notifyPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_MUTABLE);

		alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

		// alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			// @Override
			// public void onCheckedChanged (CompoundButton buttonView, boolean isChecked) {
				// String toastMassage;

				// if (isChecked) {
					// Log.d(TAG, "onCheckedChanged: IsChecked");
					// toastMassage = "Stand Up Alarm On!";

					////If the Toggle is turned on, set the repeating alarm with a 15 minute interval
					// long repeatInterval = AlarmManager.INTERVAL_HALF_HOUR;
					long repeatInterval = 1000; 
					long triggerTime = SystemClock.elapsedRealtime() + repeatInterval;
					if (alarmManager != null) {
						Log.d(TAG, "onCheckedChanged: AlarmManager!=null - Start of alarm manager");
						alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, repeatInterval, notifyPendingIntent);
					}

				// } else {
				//	   Log.d(TAG, "onCheckedChanged: IsNotChecked");
				//	   toastMassage = "Stand Up Alarm Off!";
				//	   alarmManager.cancel(notifyPendingIntent);
				//	   mNotificationManager.cancelAll();
				// }

				//Show a toast to say the alarm is turned on or off.
				// Toast.maeText(MainActivity.this, toastMassage, Toast.LENGTH_SHORT).show();
			}
		// });
	// }

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
