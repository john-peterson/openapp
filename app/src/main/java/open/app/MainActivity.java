
package open.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import open.app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
String tag = "mytag";

void log() {
	Log.e(tag, "");
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
