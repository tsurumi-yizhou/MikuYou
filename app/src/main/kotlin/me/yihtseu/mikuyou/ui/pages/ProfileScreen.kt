package me.yihtseu.mikuyou.ui.pages

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import me.yihtseu.mikuyou.model.ProfileModel

@Composable
fun ProfileScreen(viewModel: ProfileModel) {
    val state by viewModel.state.collectAsState()
    Text(state.name)
}