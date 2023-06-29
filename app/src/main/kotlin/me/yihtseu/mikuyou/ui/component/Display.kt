package me.yihtseu.mikuyou.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable
fun Display(player: ExoPlayer) {
    AndroidView(
        modifier = Modifier,
        factory = { context ->
            StyledPlayerView(context).apply {
                setPlayer(player)
            }
        }
    )
}