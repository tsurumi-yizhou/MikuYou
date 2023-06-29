@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)

package me.yihtseu.mikuyou.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import me.yihtseu.mikuyou.model.HomeModel
import me.yihtseu.mikuyou.ui.component.EntryList
import me.yihtseu.mikuyou.ui.component.TagBar
import me.yihtseu.mikuyou.ui.component.TopBar

@Composable
fun HomeScreen(viewModel: HomeModel) {
    val state by viewModel.state.collectAsState()
    Scaffold(
        modifier = Modifier,
        topBar = {
                 TopBar("Miku You", listOf(
                     me.yihtseu.mikuyou.ui.component.Icon(Icons.Outlined.AccountBox) { viewModel.gotoLogin() }
                 ))
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                TagBar { viewModel.getRegion(it) }
                EntryList(state.videos, { viewModel.gotoVideo(it.bvid) }, { viewModel.refresh() })
            }
        }
    )
}