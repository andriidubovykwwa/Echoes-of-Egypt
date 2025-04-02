package com.devname.echoesofegypt

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.devname.echoesofegypt.screen.game.GameScreen
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(application = { modules(appModule) }) {
        MaterialTheme {
            GameScreen()
        }
    }
}