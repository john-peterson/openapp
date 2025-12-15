
package open.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import java.util.logging.Logger;
import open.app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
	private ActivityMainBinding binding;
	String tag = "mytag";
	private static final Logger L = Logger.getLogger(MainActivity.class.getName());

	void log() {
		L.warning("123abc java utilities log");
		Log.e(tag, "123abc android utilities log");
	}

CountDownTimer c; 

	void timer() {
			c =	new CountDownTimer(30 * 1000, 1  * 1000) {
			public void onFinish() {
				Log.e(tag, "done");
				// TextView myTextView = (TextView) findViewById(R.id.my_text_view);
				// myTextView.setText("New Text Set from timer");
				// this.finish();
				// finish();
				// finishAndRemoveTask();
				start();
			}
			public void onTick(long t) {
				Log.e(tag, "tick " + t/1000);
			}
		};
		// }.start();
		c.start();
		// c.cancel();
	}

private Handler handler = new Handler(Looper.getMainLooper());
private Runnable runnable;

private void startNoTickTimer() {
			Log.d(tag, "Timer start");
	runnable = new Runnable() {
		@Override
		public void run() {
			Log.d(tag, "Timer finished!");
		}
	};
	handler.postDelayed(runnable, 20 * 1000);
}

private void cancelTimer() {
	handler.removeCallbacks(runnable);
}

// @Override
// protected void onDestroy() {
//     super.onDestroy();
//     cancelTimer();
// }



// public static String create_folder(Context context) {
// 	File[] directory = new File[0];
// 	directory = context.getExternalMediaDirs();
// 	for(int i = 0;i<directory.length;i++){
// 		if(directory[i].getName().contains(context.getPackageName())){
// 			return directory[i].getAbsolutePath();
// 		}
// 	}
// 	return null;
// }

void gui() {
	TextView myTextView = (TextView) findViewById(R.id.my_text_view);
	myTextView.setText("New Text Set from Java");
}


@Override
protected void onCreate(Bundle savedInstanceState) {
			Log.d(tag, "onCreate");
	super.onCreate(savedInstanceState);

	log();
	timer();
	startNoTickTimer();

	binding = ActivityMainBinding.inflate(getLayoutInflater());
	setContentView(binding.getRoot());
	// setContentView(R.layout.activity_main); // Link the XML layout
	gui();

}

@Override
protected void onDestroy() {
			Log.d(tag, "onDestroy");
	super.onDestroy();
	this.binding = null;
}

@Override
public void onPause() {
    super.onPause();
    Log.e(tag, "onPause");
	// c.cancel();
	c.start();
}

@Override
public void onResume() {
    super.onResume();
    Log.w(tag, "onResume");
    c.cancel();
}

}
