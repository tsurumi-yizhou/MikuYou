@file:OptIn(ExperimentalMaterialApi::class)

package me.yihtseu.mikuyou.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import me.yihtseu.mikuyou.api.VideoInfo

@Composable
fun EntryList(list: List<VideoInfo>, onCLick: (VideoInfo) -> Unit, onRefresh: () -> Unit) {
    var refreshing by rememberSaveable { mutableStateOf(false) }
    val refreshState = rememberPullRefreshState(refreshing, onRefresh)
    Box(modifier = Modifier.pullRefresh(refreshState)) {
        LazyColumn(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            items(list) { video ->
                Entry(video) {
                    onCLick(video)
                }
            }
        }
        PullRefreshIndicator(refreshing, refreshState, Modifier.align(Alignment.TopCenter))
    }
}