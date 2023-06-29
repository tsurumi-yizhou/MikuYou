package me.yihtseu.mikuyou.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.android.volley.RequestQueue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProfileState(
    val name: String = "",
    val face: String = "",
    val level: Int = 0,
    val sign: String = "",
)

class ProfileModel(queue: RequestQueue, navController: NavHostController) : ViewModel() {
    private val controller = navController
    private val localState = MutableStateFlow(ProfileState())
    val state = localState.asStateFlow()

    fun gotoProfile(uid: String) {
        viewModelScope.launch {
            localState.update {
                it.copy(name = uid)
            }
        }
    }
}