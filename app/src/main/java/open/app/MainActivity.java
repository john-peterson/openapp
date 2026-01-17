
package open.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import open.app.databinding.ActivityMainBinding;

import android.system.Os;
import android.system.OsConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
String tag = "mytag";

void log() {
	Log.e(tag, "");
}
void logd(String l){
Log.d(tag, l);
}
void loge(Exception e){
	Log.e(tag, e.toString());
}

void list(){
	try{
		logd("list /proc");
		final File proc = new File("/proc");
		final String[] files = proc.list();
		int i = 0;
		for(String f : files) {
			if (!new File(proc, f).isDirectory()) continue;
			try {
				// numbers are pids
				int pid =  Integer.parseInt(f);
				logd("proc "+pid+" "+name(pid));
				i++;
			} catch (NumberFormatException e) {}
		}

		} catch (Exception e) { loge(e); }
}

void kill(int pid){
	try {
	if (!self(pid))
		Os.kill(pid, OsConstants.SIGTERM); //15
	// Os.kill(pid, OsConstants.SIGABRT); //6
	} catch (Exception e) { loge(e); }
}

boolean self(int pid){
   String comm = name(pid);
   return comm.equals("open.app");
}

String name(int pid, String file){
	try {
		// File f = new File(String.format("/proc/%d/cmdline",pid));
		// File f = new File(String.format("/proc/%d/comm",pid));
		File f = new File(String.format("/proc/%d/%s",pid,file));
		FileInputStream		is = new FileInputStream(f);
		BufferedReader  reader = new BufferedReader( new InputStreamReader(is));
		String comm = reader.readLine();
		//	  logd(comm + comm.trim().length() + "com.termux".length());
		if (comm == null)
			return "null";
		else
			return comm.trim();
	}catch(Exception e) {
		loge(e);
	}
	return "null";
}


@Override
protected void onCreate(Bundle savedInstanceState) {
	log();
	list();
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
