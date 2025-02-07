
package open.app;

// import android.util.Log;

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

class Log {
static void e(String t, String s) {
	System.out.println(s);
}
}

public class Main  {
String tag = "mytag";

static StringWriter sw;
static JsonWriter out;
// static myWriter out;

void log(String s) {
	// Log.e(tag, "json test");
	System.out.println(s);
}

void json(){
	sw = new StringWriter();
	out = new JsonWriter(sw);
	out.setIndent("	");
	// out.setIndent("  ");

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
void jsonTest() throws Exception {

	// out.beginArray();
	// out.value("123");
	// out.endArray();
	
	// out.beginObject();
	// out.name("on").value(0);
	// out.endObject();

	out.beginObject();
	out.name("123");
	out.beginObject();
	out.name("on").value(0);
	out.endObject();
	out.endObject();

	// out.name("123");
	// out.beginObject();
	// out.endObject();
	
}

public Main(){
	// log("");
	json();
}

public static void main(String[] args) {
	new Main();
}

}
