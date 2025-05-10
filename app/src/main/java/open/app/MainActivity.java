
package open.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.logging.Logger;
import open.app.databinding.ActivityMainBinding;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
String tag = "mytag";
private static final Logger LOGGER = Logger.getLogger(MainActivity.class.getName());

void log() {
			LOGGER.warning("123abc java utilities log");
			Log.e(tag, "123abc android utilities log");
}

void bat(){
	BatteryManager bm = (BatteryManager) getSystemService(Context.BATTERY_SERVICE);

	Intent bs = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
	int level = bs.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
	int scale = bs.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
	int p = (level * 100) / scale;
	Log.e(tag, "battery "+p);
}

@Override
protected void onCreate(Bundle savedInstanceState) {
	log();
	bat();
	super.onCreate(savedInstanceState);

	// Inflate and get instance of binding
	binding = ActivityMainBinding.inflate(getLayoutInflater());

	// set content view to binding's root
	setContentView(binding.getRoot());
}

@Override
protected void onDestroy() {
	super.onDestroy();
	this.binding = null;
}
}
