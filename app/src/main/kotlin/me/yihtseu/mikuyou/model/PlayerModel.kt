package me.yihtseu.mikuyou.model

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.webkit.CookieManager
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.android.volley.RequestQueue
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.yihtseu.mikuyou.api.BilibiliClient
import me.yihtseu.mikuyou.api.VideoInfo
import me.yihtseu.mikuyou.media.MediaService


data class PlayerState(
    val bvid: String = "",
    val detail: VideoInfo = VideoInfo(),
    val recommended: List<VideoInfo> = listOf()
)


val factory = DefaultMediaSourceFactory(
    DefaultHttpDataSource.Factory().setDefaultRequestProperties(
        mapOf(
            "User-Agent" to "Mozilla/5.0 (iPhone; CPU iPhone OS 13_5_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1.1 Mobile/15E148 Safari/604.1",
            "Referer" to "https://www.bilibili.com/video",
            "Origin" to "https://api.bilibili.com",
            "Cookie" to CookieManager.getInstance().getCookie("api.bilibili.com")!!
        )
    )
)

class PlayerModel(context: Context, queue: RequestQueue, navigator: NavHostController): ViewModel() {
    private val client = BilibiliClient(queue)
    private val controller = navigator
    private val localState = MutableStateFlow(PlayerState())
    lateinit var player: ExoPlayer
    val state = localState.asStateFlow()

    init {
        val intent = Intent(context, MediaService::class.java)
        context.bindService(intent, object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                player = (service as MediaService.Binder).getService().player
            }
            override fun onServiceDisconnected(name: ComponentName?) {
            }
        }, ComponentActivity.BIND_AUTO_CREATE)
    }

    fun gotoVideo(bvid: String) {
        viewModelScope.launch {
            val videoInfo = client.getVideoInfo(bvid)
            val stream = client.getStreamInfo(bvid, videoInfo.cid)
            val recommended = client.getRecommended(bvid)
            player.setMediaSource(
                MergingMediaSource(
                    factory.createMediaSource(MediaItem.fromUri(stream.dash.video.last().baseUrl!!)),
                    factory.createMediaSource(MediaItem.fromUri(stream.dash.audio.last().baseUrl!!))
                )
            )
            player.prepare()
            player.play()
            localState.update {
                it.copy(
                    bvid = bvid,
                    detail = videoInfo,
                    recommended = recommended
                )
            }
        }
    }

    fun gotoProfile(uid: Long) {
        controller.navigate("profile/$uid")
    }
}