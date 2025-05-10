
package open.app;

import androidx.appcompat.app.AppCompatActivity;
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

void loge(Exception e){
	Log.e(tag, e.toString()); 
	Log.e(tag, e.getMessage());
}

void fail(){
	try {
		String s = null;
		s.equals("");
	} catch (Exception e) { loge(e); }
}

@Override
protected void onCreate(Bundle savedInstanceState) {
	log();
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
