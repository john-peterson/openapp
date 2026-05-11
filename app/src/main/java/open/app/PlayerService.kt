package open.app

import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Intent
import android.media.audiofx.LoudnessEnhancer
import android.net.Uri
import android.os.Bundle
import androidx.annotation.OptIn
import androidx.core.net.toUri
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.PlaybackParameters
import androidx.media3.common.Player
import androidx.media3.common.Player.DISCONTINUITY_REASON_AUTO_TRANSITION
import androidx.media3.common.Player.DISCONTINUITY_REASON_REMOVE
import androidx.media3.common.Player.DISCONTINUITY_REASON_SEEK
import androidx.media3.common.TrackSelectionParameters
import androidx.media3.common.Tracks
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.session.CommandButton
import androidx.media3.session.CommandButton.ICON_UNDEFINED
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import androidx.media3.session.SessionCommand
import androidx.media3.session.SessionError
import androidx.media3.session.SessionResult

@OptIn(UnstableApi::class)
@AndroidEntryPoint
class PlayerService : MediaSessionService() {

	private var mediaSession: MediaSession? = null

	// Example: Triggering a Sepia Filter effect
val customEffectCommand = SessionCommand("ACTION_APPLY_SEPIA", Bundle())

// In MediaSession.Callback
override fun onCustomCommand(
	session: MediaSession,
	controller: ControllerInfo,
	// 	controller: MediaSession.ControllerInfo,
	customCommand: SessionCommand,
	args: Bundle
): ListenableFuture<SessionResult> {
	// ): ListenableFuture<SessionResult> = serviceScope.future {

	if (customCommand.customAction == "ACTION_APPLY_SEPIA") {
		// Apply effect (e.g., using a matrix transformation)
		val sepiaEffect = MatrixTransformationEffect(sepiaMatrix)
		player.setVideoEffects(listOf(sepiaEffect))
		return Futures.immediateFuture(SessionResult(SessionResult.RESULT_SUCCESS))
	}

/*

		val command = CustomCommands.fromSessionCommand(customCommand)
		?: return@future SessionResult(SessionError.ERROR_BAD_VALUE)

		when (command) {
			CustomCommands.STOP_PLAYER_SESSION -> {
				mediaSession?.run {
					serviceScope.launch {
						mediaRepository.updateMediumPosition(
							uri = player.currentMediaItem?.mediaId ?: return@launch,
							position = player.currentPosition,
						)
					}
				}
				mediaSession?.run {
					player.clearMediaItems()
					player.stop()
				}
				stopSelf()
				return@future SessionResult(SessionResult.RESULT_SUCCESS)
			}

			CustomCommands.FLIP -> {
				val brightnessEffect = Brightness(0.5f) // Increase brightness
				val videoEffects = listOf(brightnessEffect)
				mediaSession?.run {
					// player.setVideoEffects(videoEffects)
					player.setEffects(Effects(listOf(), videoEffects)) // audioEffects, videoEffects
				}
				return@future SessionResult(SessionResult.RESULT_SUCCESS)
			}
		}
		*/

	return super.onCustomCommand(session, controller, customCommand, args)
	}
}
