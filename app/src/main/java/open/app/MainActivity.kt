
package open.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import open.app.databinding.ActivityMainBinding

import android.util.Log
import android.widget.Toast
import android.content.Context

import android.content.ContentResolver
import android.provider.Settings

import android.os.Build
import android.view.Surface
import android.view.WindowManager
import androidx.activity.ComponentActivity
// import androidx.activity.compose.LocalActivity

// jetpack compose
// androidx.compose.ui.platform.LocalContext

public class MainActivity : AppCompatActivity() {

private var _binding: ActivityMainBinding? = null

private val binding: ActivityMainBinding
	get() = checkNotNull(_binding) { "Activity has been destroyed" }

val tag = "mytag"
private var landscapeHint = true

fun bat(){
	// val contentResolver: ContentResolver = context.contentResolver
	val contentResolver: ContentResolver = getContentResolver()

	val c = Settings.Global.getString(
		contentResolver,
		"battery_saver_constants"
		// BatterySaverSecureSettings.BATTERY_SAVER_CONSTANTS
	)
	Log.e(tag, ""+c)

}

fun va(){
	var b = 1
	// if (true) val a = 1
	// val max = if (a > b) a else b
	b = 1
	// a ++
}

fun wen(){
	val x = 0
	val result = when (x) {
		1 -> "One"
		2 -> "Two"
		else -> "Something else" // The default case
	}
}

fun rotateLong() {
	val rotation = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
		display?.rotation
	} else {
		val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
		windowManager.defaultDisplay.rotation
	}

	// val activity = LocalActivity.current as ComponentActivity
	// activity.requestedOrientation = when (rotation) {
		
	// requestedOrientation = when (rotation) {
	// 	Surface.ROTATION_90 -> ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
	// 	Surface.ROTATION_270 -> ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
	// 	else ->  ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
	// }

}

fun tost(){
	val text = "long press to reverse landscape "
	// Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
	// Toast.makeText(activity, text).show()
	Toast.makeText(this, text, 0).show()
	landscapeHint = false
}

override fun onCreate(savedInstanceState: Bundle?) {
	// Log.e(tag, "")
	// bat();
	// wen();
	tost()

	super.onCreate(savedInstanceState)

	// Inflate and get instance of binding
	_binding = ActivityMainBinding.inflate(layoutInflater)

	// set content view to binding's root
	setContentView(binding.root)
}

override fun onDestroy() {
	super.onDestroy()
	_binding = null
}

} //class
