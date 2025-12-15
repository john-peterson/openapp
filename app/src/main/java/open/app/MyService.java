
package open.app;
import android.util.Log;
import android.widget.Toast;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import android.Manifest;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;



public class MyService extends Service {
	private static final String tag = "mytag";
	public static final String CHANNEL_ID = "ForegroundServiceChannel";

	void listen(){
		BroadcastReceiver myReceiver;
		myReceiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(PowerManager.ACTION_DEVICE_IDLE_MODE_CHANGED);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		registerReceiver(myReceiver, filter);
	}


	@Override
	public void onCreate() {
		super.onCreate();
		// listen();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
	Log.e(tag, "onStartCommand");
		// Create the notification channel (required for Android O and above)
		createNotificationChannel();

		Intent notificationIntent = new Intent(this, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this,
				0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

		Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
				.setContentTitle("Foreground Service is Running")
				.setContentText("Performing long-running task...")
				.setSmallIcon(R.drawable.ic_launcher_foreground)
				.setContentIntent(pendingIntent)
				.setOngoing(true) // Makes the notification persistent
				.build();

		// Call startForeground to promote the service
		// You must specify the foreground service type here as well from Android 14+
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
			// Use ServiceCompat for compatibility or specific API methods
			// For Android 14+, the type needs to match the manifest declaration
			startForeground(1, notification, getForegroundServiceType()); // Example type handling
		} else {
			startForeground(1, notification);
		}

		// Do your long-running work here...

		// Use START_STICKY to restart the service if the system kills it
		return START_STICKY;
	}

	// ... (helper method to get the correct foreground service type integer if needed)

	@Override
	public void onDestroy() {
		super.onDestroy();
		// Call stopForeground(true) to remove the notification when stopping the service
		stopForeground(true);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private void createNotificationChannel() {

// Source - https://stackoverflow.com/q
// Posted by zaxunobi
// Retrieved 2026-01-11, License - CC BY-SA 4.0

 if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) { 
	 Toast.makeText(getApplicationContext(), "not granted", Toast.LENGTH_LONG).show();

			// TODO: Consider calling
			//	  ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//	 public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//											int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return;
		}


		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			CharSequence name = "Foreground Service Channel";
			String description = "Channel for foreground service notifications";
			int importance = NotificationManager.IMPORTANCE_DEFAULT;
			NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
			channel.setDescription(description);
			NotificationManager manager = getSystemService(NotificationManager.class);
			manager.createNotificationChannel(channel);
		}
	}
}

