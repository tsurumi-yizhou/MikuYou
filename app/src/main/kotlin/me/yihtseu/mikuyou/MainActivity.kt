package me.yihtseu.mikuyou

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import me.yihtseu.mikuyou.model.ModelFactory
import me.yihtseu.mikuyou.ui.pages.HomeScreen
import me.yihtseu.mikuyou.ui.pages.LoginScreen
import me.yihtseu.mikuyou.ui.pages.PlayerScreen
import me.yihtseu.mikuyou.ui.pages.ProfileScreen
import me.yihtseu.mikuyou.ui.theme.MikuYouTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MikuYouTheme {
                val controller = rememberNavController()
                val factory = ModelFactory(this, controller)
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavHost(controller, "home") {
                        composable("weblogin") {
                            LoginScreen(factory.loginModel)
                        }
                        composable("home") {
                            HomeScreen(factory.homeModel)
                        }
                        composable(
                            route = "player/{bvid}",
                            arguments = listOf(navArgument("bvid") { type = NavType.StringType} )
                        ) {
                            PlayerScreen(factory.playerModel.apply { gotoVideo(it.arguments!!.getString("bvid")!!) })
                        }

                        composable(
                            route = "profile/{uid}",
                            arguments = listOf(navArgument("uid") { type = NavType.StringType} )
                        ) {
                            factory.profileModel.gotoProfile(it.arguments!!.getString("uid")!!)
                            ProfileScreen(factory.profileModel)
                        }
                    }
                }
            }
        }
    }
}