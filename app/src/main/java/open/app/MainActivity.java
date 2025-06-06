
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

	public static String create_folder(Context context) {
		File[] directory = new File[0];
		directory = context.getExternalMediaDirs();
		for(int i = 0;i<directory.length;i++){
			if(directory[i].getName().contains(context.getPackageName())){
				return directory[i].getAbsolutePath();
			}
		}
		return null;
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
