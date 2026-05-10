
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

import androidx.media3.common.Player
import androidx.media3.common.VideoSize
import androidx.media3.effect.Brightness
import androidx.media3.effect.Effects
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaController
import androidx.media3.session.MediaSessionService
import androidx.media3.ui.PlayerView


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

fun play(){
	// Create the player instance
	val player = ExoPlayer.Builder(this).build()
	Log.e(tag, player::class.simpleName+" "+player::class.qualifiedName)
	Log.e(tag, player::class.qualifiedName!!)

	// player.videoSize.isPortrait
	player.isPlaying
	player.setPlaybackSpeed(1.0f)
	// player.getIsLoudnessGainSupported()
	
	val playerView = findViewById<PlayerView>(R.id.player_view)
	playerView.player = player

	// Build a media item from a URI (URL or local path)
	// val mediaItem = MediaItem.fromUri("https://example.com/video.mp4")

	// player.setMediaItem(mediaItem)
	// player.prepare()
	// player.playWhenReady = true

	// val bright = brightness.coerceIn(0f, maxBrightness)
	val brightnessEffect = Brightness(0.5f) // Increase brightness
	// val brightnessEffect = Brightness(brightness) // Increase brightness
	// val brightnessEffect = Brightness(bright) // Increase brightness
	// val brightnessEffect = Brightness(brightness.coerceIn(0f, maxBrightness))
	val videoEffects = listOf(brightnessEffect)
	// Applying to an ExoPlayer instance
	// val exoPlayer = player as? ExoPlayer
	player.setVideoEffects(videoEffects)
}

fun con(){
	controllerFuture.addListener({
		val mediaController = controllerFuture.get()
		// mediaController implements the Player interface
		mediaController.play() 
	}, MoreExecutors.directExecutor())

	// Example: Creating and using a MediaController
	val sessionToken = SessionToken(context, ComponentName(context, PlaybackService::class.java))
	val controllerFuture = MediaController.Builder(context, sessionToken).buildAsync()

	// Create a list of effects
	val videoEffects = mutableListOf<Effect>()
	videoEffects.add(RgbFilter.createGrayscaleFilter())
	videoEffects.add(ScaleAndRotateTransformation.Builder().setScale(.5f, .5f).build())

	val mediaItem = player.currentMediaItem ?: return
	val editedMediaItem = EditedMediaItem.Builder(mediaItem)
	.setEffects(Effects(listOf(), effects)) // audioEffects, videoEffects
	.build()

	// Add the edited item to your player
	player.setMediaItem(editedMediaItem.mediaItem)
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

	play()
}

override fun onDestroy() {
	super.onDestroy()
	_binding = null
}

} //class
