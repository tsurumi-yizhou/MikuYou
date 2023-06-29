package me.yihtseu.mikuyou.media

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationCompat
import androidx.media.session.MediaButtonReceiver
import com.google.android.exoplayer2.ExoPlayer
import me.yihtseu.mikuyou.MainActivity
import me.yihtseu.mikuyou.R

private const val channelId = "a897a89e-d17a-4541-9ffc-920535d1f7ac"


class MediaService : Service() {
    lateinit var player: ExoPlayer

    override fun onCreate() {
        super.onCreate()
        player = ExoPlayer.Builder(this).build()
        // init notification manager
        val channel = NotificationChannel(channelId, "Miku You Media Channel", NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = "Miku You Media Channel"
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    private fun showNotification(state: Int) {
        val builder = NotificationCompat.Builder(this, channelId).apply {
            setContentTitle("Test Title")
            setContentText("Test Content Text")
            setContentIntent(
                PendingIntent.getActivity(
                    this@MediaService,
                    0,
                    Intent(this@MediaService, MainActivity::class.java),
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
                )
            )
            setAutoCancel(false)
            setSmallIcon(R.mipmap.ic_launcher)
            setPriority(NotificationCompat.PRIORITY_DEFAULT)
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            when (state) {
                PlaybackStateCompat.STATE_PLAYING -> addAction(
                    NotificationCompat.Action(
                        R.drawable.play, "Play", MediaButtonReceiver.buildMediaButtonPendingIntent(
                            this@MediaService, PlaybackStateCompat.ACTION_PLAY_PAUSE
                        )
                    )
                )
                PlaybackStateCompat.STATE_PAUSED -> addAction(
                    NotificationCompat.Action(
                        R.drawable.stop, "Pause", MediaButtonReceiver.buildMediaButtonPendingIntent(
                            this@MediaService, PlaybackStateCompat.ACTION_PLAY_PAUSE
                        )
                    )
                )
            }
        }
        startForeground(NotificationCompat.FLAG_FOREGROUND_SERVICE, builder.build())
    }

    inner class Binder : android.os.Binder() {
        fun getService(): MediaService {
            return this@MediaService
        }
    }

    private val binder = Binder()

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }
}