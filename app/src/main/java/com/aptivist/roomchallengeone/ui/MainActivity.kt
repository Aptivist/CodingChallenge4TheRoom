package com.aptivist.roomchallengeone.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aptivist.roomchallengeone.ui.meme.MemeView
import com.aptivist.roomchallengeone.ui.theme.RoomChallengeOneTheme
import org.koin.android.ext.android.getKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RoomChallengeOneTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "memes"){
                    composable(Screen.Memes.route) { MemeView() }
                }

            }
        }
    }
}

sealed class Screen(val route: String) {
    object Memes : Screen("meme")
}