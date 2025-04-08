package com.devname.echoesofegypt

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.devname.echoesofegypt.screen.Navigation
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(application = { modules(appModule) }) {
        MaterialTheme {
            Navigation()
        }
    }
}