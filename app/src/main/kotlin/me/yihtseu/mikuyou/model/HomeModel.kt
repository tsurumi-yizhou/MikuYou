package me.yihtseu.mikuyou.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.android.volley.RequestQueue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.yihtseu.mikuyou.api.BilibiliClient
import me.yihtseu.mikuyou.api.VideoInfo

data class HomeState(
    val videos: List<VideoInfo> = listOf(),
    val page: Int = 1,
    val tid: Int = 1
)

class HomeModel(queue: RequestQueue, NavController: NavHostController): ViewModel() {
    private val client = BilibiliClient(queue)
    private val controller = NavController
    private val localState = MutableStateFlow(HomeState())
    val state = localState.asStateFlow()

    init {
        getRegion(0)
    }

    fun getRegion(tid: Int) {
        viewModelScope.launch {
            val videos = if (tid != 0) client.getRegion(tid).archives else client.getPopularInfo().list
            localState.update {
                HomeState(videos = videos, page = 1, tid = tid)
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            val current = localState.value
            val videos = if (current.tid == 0) client.getPopularInfo(current.page + 1).list else client.getRegion(current.tid, current.page + 1).archives
            localState.update {
                it.copy(page = current.page + 1, videos = videos)
            }
        }
    }

    fun gotoVideo(bvid: String) {
        controller.navigate("player/$bvid")
    }

    fun gotoLogin() {
        controller.navigate("weblogin")
    }
}