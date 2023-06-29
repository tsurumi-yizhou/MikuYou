@file:OptIn(ExperimentalMaterial3Api::class)

package me.yihtseu.mikuyou.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import me.yihtseu.mikuyou.model.PlayerModel
import me.yihtseu.mikuyou.ui.component.Display
import me.yihtseu.mikuyou.ui.component.Intro
import me.yihtseu.mikuyou.ui.component.Item
import me.yihtseu.mikuyou.ui.component.TopBar


@Composable
fun PlayerScreen(viewModel: PlayerModel) {
    val state by viewModel.state.collectAsState()
    val uploader = state.detail.owner
    Scaffold(
        modifier = Modifier,
        topBar = {
            TopBar(state.detail.title)
        },
        content = { paddingValue ->
            Column (
                modifier = Modifier.padding(paddingValue)
            ) {
                Display(viewModel.player)
                LazyColumn(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    item {
                        Intro(uploader.name, uploader.face, state.detail.desc, uploader.mid) {
                            viewModel.gotoProfile(it)
                        }
                    }
                    state.recommended.forEach { video ->
                        item {
                            Item(video) {
                                viewModel.gotoVideo(video.bvid)
                            }
                        }
                    }
                }
            }
        }
    )
}