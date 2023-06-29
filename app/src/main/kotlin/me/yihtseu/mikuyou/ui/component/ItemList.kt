package me.yihtseu.mikuyou.ui.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import me.yihtseu.mikuyou.api.VideoInfo

@Composable
fun ItemList(recommended: List<VideoInfo>, onCLick: (VideoInfo) -> Unit) {
    LazyColumn {
        recommended.forEach { video ->
            item {
                Item(video) { onCLick(video) }
            }
        }
    }
}