package open.app;

import android.content.Context;
import java.lang.reflect.Method;

public class Main { 
	
	public static Context getSystemContext() {
		try {
			Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
			Method method = activityThreadClass.getMethod("systemMain");
			Object activityThread = method.invoke(null);
			Method getSystemContextMethod = activityThreadClass.getMethod("getSystemContext");
			return (Context) getSystemContextMethod.invoke(activityThread);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Main(){
		System.out.println("abc");
	}
	public static void main(String[] args) {
		new Main();
	}
}
