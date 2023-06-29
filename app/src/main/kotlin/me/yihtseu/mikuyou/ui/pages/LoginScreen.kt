package me.yihtseu.mikuyou.ui.pages

import android.webkit.CookieManager
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.yihtseu.mikuyou.model.LoginModel
import me.yihtseu.mikuyou.ui.component.WebLogin

@Composable
fun LoginScreen(viewModel: LoginModel) {
    var show by rememberSaveable { mutableStateOf(true) }
    val state by viewModel.state.collectAsState()
    if (state.error) {
        AlertDialog(
            onDismissRequest = {
                viewModel.exit()
                               },
            confirmButton = {
                Button(onClick = {
                    CookieManager.getInstance().removeAllCookies {
                        viewModel.exit()
                    }
                }) {
                    Text("清除Cookie")
                }
            },
            dismissButton = {
                Button(onClick = { viewModel.exit() }) {
                    Text("取消")
                }
            }
        )
    }
    if (show) {
        WebLogin {
            show = false
            GlobalScope.launch {
                try {
                    viewModel.check()
                } catch (e: Exception) {
                    viewModel.set(e.message.toString())
                }
            }
        }
    }
}