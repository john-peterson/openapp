package open.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PowerManager;
import android.provider.AlarmClock;
import android.util.Log;
import android.widget.Toast;
import java.lang.reflect.Field;
import java.util.Arrays;

public class MyReceiver extends BroadcastReceiver {
	String tag = "mytag";
	// private static final String TAG = "MyBroadcastReceiver";
	static Context context;
	// int i=0;
	static int i=0;
	
void log(String s) {
	Log.d(tag, s);
}

// @Override
// protected void onCreate(Bundle savedInstanceState) {
// protected void onCreate( ) {
// 	log("onCreate receiver");
// }

@Override
public void onReceive(Context c, Intent intent) {
	context = c;
try{
	// context.status();
	String action = intent.getAction();
	if (action == null) return;
	int id = findId(intent);
	log("onReceive "
			// +intent
			// +"action="+action
			// + " "+MainActivity.status()
			+intent(intent)
			// +flags(intent)
			+" id=" +id
	// Log.e(tag, intentToString(intent));
			);

	// if (AlarmClock.ACTION_SET_ALARM.equals(intent.getAction())) {
	// if (action == "android.intent.action.SET_ALARM" ) {
	if (action == "alarm" ) {
		i++;
		log("ALARM"+i);
		// cancelAlarm(id, intent);
		MainActivity.closeApp();
	}
} catch (Exception e) {
	log(""+e);
}
}

int findId(Intent intent){
for (int i =0; i<  10; i++){
	if (alarmUp(intent, i))
		return i;
}
return -1;
}

boolean alarmUp(Intent intent, int id){
	int flags = PendingIntent.FLAG_NO_CREATE|PendingIntent.FLAG_MUTABLE;
	// int flags = PendingIntent.FLAG_NO_CREATE;
	// boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent, flags) != null);
	PendingIntent alarmUp = PendingIntent.getBroadcast(context, id, intent, flags);
	// alarmToggle.setChecked(alarmUp);
	return alarmUp != null;
}

void cancelAlarm(int id, Intent intent){
	log("cancel alarm "+id+" "+intent);
	// MainActivity.cancel();
	// MainActivity a = (MainActivity) context;
	// MainActivity a = MainActivity.get();
	// a.cancel();
		
	AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
	// Intent intent = new Intent(this, MyReceiver.class);
	// Intent intent = new Intent(context, this); 
	// Intent intent = new Intent();
	// intent.setClass(this, MyReceiver.class); 
	// intent.setClass(MyReceiver.this, MyReceiver.class); 
	// intent.setClass(context, MyReceiver.class); 
	// intent.setAction("alarm");
	int flags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE;
	PendingIntent pi = PendingIntent.getBroadcast(context , id, intent, flags);
	am.cancel(pi);
	// am.cancel(intent);
	// am.cancelRepeating(pi);
}


public static String intentToString(Intent intent) {
	if (intent == null) {
		return null;
	}
	return intent.toString() + " " + bundleToString(intent.getExtras());
}

public static String bundleToString(Bundle bundle) {
	StringBuilder out = new StringBuilder("Extras {");
	if (bundle == null) {
		out.append("null");
	} else {
		boolean first = true;
		for (String key : bundle.keySet()) {
			if (!first) {
				out.append(", ");
			}
			out.append(key).append('=');
			Object value = bundle.get(key);
			if (value instanceof int[]) {
				out.append(Arrays.toString((int[]) value));
			} else if (value instanceof byte[]) {
				out.append(Arrays.toString((byte[]) value));
			} else if (value instanceof boolean[]) {
				out.append(Arrays.toString((boolean[]) value));
			} else if (value instanceof short[]) {
				out.append(Arrays.toString((short[]) value));
			} else if (value instanceof long[]) {
				out.append(Arrays.toString((long[]) value));
			} else if (value instanceof float[]) {
				out.append(Arrays.toString((float[]) value));
			} else if (value instanceof double[]) {
				out.append(Arrays.toString((double[]) value));
			} else if (value instanceof String[]) {
				out.append(Arrays.toString((String[]) value));
			} else if (value instanceof CharSequence[]) {
				out.append(Arrays.toString((CharSequence[]) value));
			} else if (value instanceof Parcelable[]) {
				out.append(Arrays.toString((Parcelable[]) value));
			} else if (value instanceof Bundle) {
				out.append(bundleToString((Bundle) value));
			} else {
				out.append(value);
			}

            first = false;
        }
    }
    out.append("}");
    return out.toString();
}

String flags(Intent intent) {
	String f="";
	Field[] declaredFields = Intent.class.getDeclaredFields();
	for (Field field : declaredFields) {
		if (field.getName().startsWith("FLAG_")) {
			try {
				int flag = field.getInt(null);
				if ((intent.getFlags() & flag) != 0) {
					f+=(", "+field.getName());
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	return " flags { "+f+" }";
}

public static String intent(Intent i){
	Bundle bundle = i.getExtras();
	String s = "";
	if (bundle == null) return ""+i;
	for (String key : bundle.keySet()) {
		// Log.e(tag, key + " : " + (bundle.get(key) != null ? bundle.get(key) : "NULL"));
		s += ", "+	key + " : " + (bundle.get(key) != null ? bundle.get(key) : "NULL");
	}
	return i+" extras { "+s+" }";
}

} //class

