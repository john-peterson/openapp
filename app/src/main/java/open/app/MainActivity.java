
package open.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import open.app.databinding.ActivityMainBinding;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
String tag = "mytag";

void log() {
	Log.e(tag, "123abc android utilities log");
}

int test = 0;
void test() { test++;}

void bat(){
	BatteryManager bm = (BatteryManager) getSystemService(Context.BATTERY_SERVICE);

	Intent bs = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
	double l = bs.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
	double s = bs.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
	double p = (l * 100.0) / s;
	Log.e(tag, "battery "+l+" "+s+" "+p);
	Log.e(tag, String.format("battery %f, %f, %f",l,s,p));
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
