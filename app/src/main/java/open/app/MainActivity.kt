
package open.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import open.app.databinding.ActivityMainBinding

import android.util.Log
// import android.content.Context

import android.content.ContentResolver
import android.provider.Settings

public class MainActivity : AppCompatActivity() {

	private var _binding: ActivityMainBinding? = null
	
	private val binding: ActivityMainBinding
	  get() = checkNotNull(_binding) { "Activity has been destroyed" }
	
		val tag = "mytag"

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

	
	override fun onCreate(savedInstanceState: Bundle?) {
		// Log.e(tag, "")
		bat();
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
}
