// package mohammad.samandari.standup;
package open.app; 

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

	private NotificationManager mNotificationManager;
	private static final int NOTIFICATION_ID = 0;
	private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
	private static final String TAG = "mytag";


	@Override
	public void onReceive (Context context, Intent intent) {
	try{
			Log.d(TAG, String.format("onReceive:%s %s",
									intent.toString(),
									intent.getName()
									// intent.getStringExtra(AlarmClock.EXTRA_MESSAGE)
									));
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
					for (String key : bundle.keySet()) {
							Log.e(TAG, key + " : " + (bundle.get(key) != null ? bundle.get(key) : "NULL"));
					}
			}

		// mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		// deliverNotification(context);
		} catch (Exception e) {

				Log.d(TAG, e.toString());
		}
	}

	private void deliverNotification (Context context) {
		Log.d(TAG, "deliverNotification: AlarmReceiver Class");

		Intent contentIntent = new Intent(context, MainActivity.class);
		PendingIntent contentPendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
				// .setSmallIcon(R.drawable.ic_stand_up)
				.setContentTitle("Stand Up Alert")
				.setContentText("You should stand up and walk around now!")
				.setContentIntent(contentPendingIntent)
				.setPriority(NotificationCompat.PRIORITY_HIGH)
				.setAutoCancel(true)
				.setDefaults(NotificationCompat.DEFAULT_ALL);

		mNotificationManager.notify(NOTIFICATION_ID, builder.build());

	}

}
