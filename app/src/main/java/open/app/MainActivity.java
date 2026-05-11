
package open.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import open.app.databinding.ActivityMainBinding;

import android.util.JsonWriter;
import java.util.List;
// import java.io.PrintWriter;
import java.io.StringWriter;

class myWriter {
	StringWriter sw;
	public myWriter(StringWriter s) {
		sw = s;
	}
	void value(String s) {
		sw.append(s);
	}
}

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
String tag = "mytag";

static StringWriter sw;
// static JsonWriter out;
static myWriter out;

void log() {
	Log.e(tag, "json test");
}


void json(){
	sw = new StringWriter();
	// out = new JsonWriter(sw);
	// out.setIndent("  ");
	// out.setIndent("	");

	try {
		jsonTest();
		// out.endObject();
		Log.e(tag, sw.toString());
	} catch (Exception e) {
		Log.e(tag, e.toString());
	}
}

// # [ array of values
// # { object of key value pair
static void jsonTest() throws Exception {
	out.beginArray();
	out.value("123");
	out.endArray();
	
	// out.beginObject();
	// out.name("on").value(0);
	// out.endObject();

	// out.beginObject();
	// out.name("123");
	// out.name("on").value(0);
	
}

@Override
protected void onCreate(Bundle savedInstanceState) {
	log();
	json();
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
