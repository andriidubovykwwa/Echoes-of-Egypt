package com.devname.echoesofegypt.screen.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.devname.echoesofegypt.screen.Screen

@Composable
fun MenuScreen(navController: NavController) {
    Box(Modifier.fillMaxSize()) {
        Column {
            Button(onClick = { navController.navigate(Screen.Game) }) {
                Text(text = "Play")
            }
            Button(onClick = { navController.navigate(Screen.Achievements) }) {
                Text(text = "Achievements")
            }
            Button(onClick = { navController.navigate(Screen.Tutorial) }) {
                Text(text = "Tutorial")
            }
        }
    }
}
