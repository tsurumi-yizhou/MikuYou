package me.yihtseu.mikuyou.model

import android.content.Context
import androidx.navigation.NavHostController
import com.android.volley.toolbox.Volley
import me.yihtseu.mikuyou.api.BilibiliClient

data class ErrorState(
    val show: Boolean = false,
    val message: String = ""
)

class ModelFactory(context: Context, controller: NavHostController) {
    val queue = Volley.newRequestQueue(context.applicationContext)
    val client = BilibiliClient(Volley.newRequestQueue(context.applicationContext))
    val playerModel = PlayerModel(context, queue, controller)
    val profileModel = ProfileModel(queue, controller)
    val homeModel = HomeModel(queue, controller)
    val loginModel = LoginModel(queue, controller)
}