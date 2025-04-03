package com.devname.echoesofegypt.screen.achievements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.devname.echoesofegypt.screen.Screen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AchievementsScreen(
    navController: NavController,
    viewModel: AchievementsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    Box(Modifier.fillMaxSize()) {
        Button(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = { navController.popBackStack() }) {
            Text(text = "Back")
        }
        Text(modifier = Modifier.align(Alignment.Center), text = state.toString())
    }
}
