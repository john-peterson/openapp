
package open.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.os.Bundle;
import android.util.Log;
import java.util.logging.Logger;
import open.app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
	private ActivityMainBinding binding;
	String tag = "mytag";
	private static final Logger LOGGER = Logger.getLogger(MainActivity.class.getName());

	void log() {
        LOGGER.warning("123abc java utilities log");
				Log.e(tag, "123abc android utilities log");
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
		super.onCreate(savedInstanceState);

		// Inflate and get instance of binding
		binding = ActivityMainBinding.inflate(getLayoutInflater());

		// set content view to binding's root
		setContentView(binding.getRoot());
		day();
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.binding = null;
	}
}
