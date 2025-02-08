package open.app;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import java.util.LinkedHashSet;

import open.app.MainActivity;

public class KeyInterceptor extends AccessibilityService {
    LinkedHashSet<Integer> pressedKeys = new LinkedHashSet<>();

    private static final Handler handler = new Handler(Looper.getMainLooper());
    private static KeyInterceptor self;
    private static MainActivity main;
    private static boolean launchedAutomatically = false;
    private boolean enabled = false;

    public KeyInterceptor() {
        self = this;
    }

    public static void setContext(MainActivity c) {
        main = c;
    }

    public static boolean isLaunched() {
        AccessibilityServiceInfo info = self == null ? null : self.getServiceInfo();
        return info != null && info.getId() != null;
    }

    public static boolean isCap(Context c) {
        if (!isLaunched()) return false;
        AccessibilityServiceInfo info = self.getServiceInfo();
        int caps = info.getCapabilities();

        boolean flag = (info.flags & AccessibilityServiceInfo.FLAG_REQUEST_FILTER_KEY_EVENTS) != 0;
        boolean cap = (caps & AccessibilityServiceInfo.CAPABILITY_CAN_REQUEST_FILTER_KEY_EVENTS) != 0;
		Toast.makeText(self, "cap flags=" + flag + " ,cap=" + cap,Toast.LENGTH_SHORT).show();

    return cap && flag;
    }
    //
            
    private void enable(){
        AccessibilityServiceInfo info = self.getServiceInfo();

        info.packageNames =new String[] { "com.termux" }; 
        // info.packageNames = new String[] { "" }; 
        // info.packageNames = null; 

        // info.flags = AccessibilityServiceInfo.DEFAULT;
    // info.flags |= AccessibilityServiceInfo.FLAG_REQUEST_FILTER_KEY_EVENTS;

        // info.eventTypes = 
         // AccessibilityEvent.TYPES_ALL_MASK;
        // AccessibilityEvent.TYPE_VIEW_CLICKED
        // | AccessibilityEvent.TYPE_VIEW_FOCUSED
                ;

        int cap = info.getCapabilities();
        // info.setCapabilities(cap | AccessibilityServiceInfo.CAPABILITY_CAN_REQUEST_FILTER_KEY_EVENTS);
        // setServiceInfo(new AccessibilityServiceInfo() {{ flags = FLAG_REQUEST_FILTER_KEY_EVENTS; }});
        self.enabled = true;
        self.setServiceInfo(info);

    }
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
		// Toast.makeText(this, "connected",Toast.LENGTH_SHORT).show();
    // enable();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "start",Toast.LENGTH_SHORT).show();
        int result = super.onStartCommand(intent, flags, startId);
        return result;
    }

    /*
    @Override
	public boolean onKeyDown(int key, KeyEvent event) {
		Toast.makeText(this, "access down"+ key + " ",Toast.LENGTH_SHORT).show();
		return super.onKeyDown(key, event);
  }*/
   
void makeNoise(){
    startActivity(new Intent(Intent.ACTION_VOICE_COMMAND).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    // Start Activity with  Dialog Theme (android:theme="@android:style/Theme.Dialog")
}


// @SuppressWarnings("all")
// @SuppressWarnings("unreachable")
    @Override
    public boolean onKeyEvent(KeyEvent event) {
        int id = event.getDeviceId();
        InputDevice device = InputDevice.getDevice(id);
        String name = device.getName();
        if (name.equals("fp-keys")) {
        // Log.e("fp-keys", "swallowed code=" + code);
        // makeNoise();
        return true;
        }
        // return false;
        /*
        boolean down = event.getAction() == KeyEvent.ACTION_DOWN;
        if (!down) return false;
        int code = event.getScanCode();
        int key = event.getKeyCode();
        int source = event.getSource();
// KeyEventDetectionSource.ACCESSIBILITY_SERVICE,  
        boolean button = source == InputDevice.SOURCE_CLASS_BUTTON;
        // getKeyboardType()
        int flags = event.getFlags();
        boolean system = (flags & KeyEvent.FLAG_FROM_SYSTEM) != 0;
        boolean hard = (flags & KeyEvent.FLAG_VIRTUAL_HARD_KEY) != 0;
        boolean soft = (flags & KeyEvent.FLAG_SOFT_KEYBOARD) != 0;
        // KetCharacterMap map = event.getKeyCharacterMap();
        // KeyCharacterMap kmap = KeyCharacterMap.load(device);
        AccessibilityServiceInfo info = self.getServiceInfo();
        String out = "event string=" + event.toString() + ", system=" + system + ", hard=" + hard  + ", name=" + name;
		// Toast.makeText(this, "event pkg=" + info.packageNames.toString(),Toast.LENGTH_SHORT).show();
       try { 
            new AlertDialog.Builder(main).setTitle("").setMessage(out).setNegativeButton("OK", null).create().show();
       } catch (Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
       }

        // Toast.makeText(this,out ,Toast.LENGTH_SHORT).show();
    // if (key == 111) return true;
    */
		return super.onKeyEvent(event);
    }


    @Override
    public void onAccessibilityEvent(AccessibilityEvent e) {
		Toast.makeText(this, "event type=" + e.getEventType(),Toast.LENGTH_SHORT).show();

    // switch(type) {
    //      case AccessibilityEvent.TYPE_VIEW_CLICKED:
    //          do somthing
    //          break;
    // }

    // super.onAccessibilityEvent(e);
    }

    @Override
    public void onInterrupt() {
		Toast.makeText(this, "int" ,Toast.LENGTH_SHORT).show();
    }
}
