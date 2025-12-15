package open.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
	String tag = "mytag";
	// private static final String TAG = "MyBroadcastReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		// context.status();
		Log.e(tag, String.format("%s {%s}",intent.getAction().toString(),MainActivity.status()));

		if (PowerManager.ACTION_DEVICE_IDLE_MODE_CHANGED.equals(intent.getAction())) {
			PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
			boolean isIdle = pm.isDeviceIdleMode(); // Check the current state
			if (isIdle) {
				// Device entered idle mode (Doze)
				Log.e(tag, "idle");
			} else {
				// Device exited idle mode
				Log.e(tag, "active");
			}
		}
	}

		/*
	@Override
	public void onReceive(Context context, Intent intent) {
		// This method is called when the BroadcastReceiver receives an Intent broadcast.
		if (intent.getAction() != null) {
			Log.d(TAG, "Received action: " + intent.getAction());
			Toast.makeText(context, "Received broadcast: " + intent.getAction(), Toast.LENGTH_SHORT).show();

			// Handle specific actions
			if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {
				boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
				Log.d(TAG, "Airplane mode changed: " + (isAirplaneModeOn ? "On" : "Off"));
			}
		}
		*/

}

