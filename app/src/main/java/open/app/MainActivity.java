
package open.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.os.Bundle;
import android.util.Log;
import java.util.logging.Logger;
import open.app.databinding.ActivityMainBinding;

import android.app.UiModeManager;
import android.content.Context;

import android.content.ContentResolver;
import android.provider.Settings;

public class MainActivity extends AppCompatActivity {
	private ActivityMainBinding binding;
	String tag = "mytag";
	private static final Logger LOGGER = Logger.getLogger(MainActivity.class.getName());

	void log() {
        LOGGER.warning("123abc java utilities log");
				Log.e(tag, "123abc android utilities log");
	}

	/*
	void	parse() {
		String batterySaverConstants = Settings.Global.getString(contentResolver,
        Settings.Global.BATTERY_SAVER_DEVICE_SPECIFIC_CONSTANTS);

		String[] constants = batterySaverConstants.split(",");
		for (String constant : constants) {
			if (constant.trim().startsWith("enable_night_mode=")) {
				String value = constant.trim().substring("enable_night_mode=".length());
				boolean enableNightMode = Boolean.parseBoolean(value);
				// Use the boolean value
				break;
			}
		}
	}
	*/


	void bat() {
		// private val contentResolver: ContentResolver = context.contentResolver
		ContentResolver contentResolver = getContentResolver(); // or context.getContentResolver()

		// var val = {
		// 	Settings.Global.DEVICE_IDLE_CONSTANTS,
		// 	Settings.Global.AIRPLANE_MODE_RADIOS;
		// };

		String c = Settings.Global.getString(
				contentResolver,
				// "battery_saver_constants"
        // Settings.Global.BATTERY_SAVER_DEVICE_SPECIFIC_CONSTANTS
        Settings.Global.BATTERY_SAVER_CONSTANTS
				);
		Log.e(tag, ""+c);

		String a = Settings.Global.getString(contentResolver, Settings.Global.AIRPLANE_MODE_RADIOS);
		Log.e(tag, ""+a);
	}

	void day() {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
// setNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

		UiModeManager uiManager = (UiModeManager) getSystemService(Context.UI_MODE_SERVICE);
		uiManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
		// uiManager.setNightMode(UiModeManager.MODE_NIGHT_NO);
		// uiManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_AUTO);
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
		// day();
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.binding = null;
	}
}
