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
import me.yihtseu.mikuyou.api.LoginInfo

data class LoginState(
    val info: LoginInfo = LoginInfo(),
    val error: Boolean = false,
    val message: String = ""
)

class LoginModel(queue: RequestQueue, navigator: NavHostController) : ViewModel() {
    private val client = BilibiliClient(queue)
    private val controller = navigator
    private val localState = MutableStateFlow(LoginState())
    val state = localState.asStateFlow()

    fun check() {
        viewModelScope.launch {
            try {
                client.checkLogin()
            } catch (e: Exception) {
                localState.update {
                    it.copy(error = true, message = e.message.toString())
                }
            }
        }
    }

    fun set(msg: String) {
        localState.update {
            it.copy(error = true, message = msg)
        }
    }

    fun exit() {
        localState.update {
            it.copy(error = false)
        }
        controller.navigate("home")
    }
}