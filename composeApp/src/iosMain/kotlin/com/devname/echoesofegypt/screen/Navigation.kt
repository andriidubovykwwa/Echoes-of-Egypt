package com.devname.echoesofegypt.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devname.echoesofegypt.screen.achievements.AchievementsScreen
import com.devname.echoesofegypt.screen.game.GameScreen
import com.devname.echoesofegypt.screen.menu.MenuScreen
import com.devname.echoesofegypt.screen.tutorial.TutorialScreen
import kotlinx.serialization.Serializable

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Screen.Menu
    ) {
        composable<Screen.Menu> { MenuScreen(navController) }
        composable<Screen.Game> { GameScreen(navController) }
        composable<Screen.Achievements> { AchievementsScreen(navController) }
        composable<Screen.Tutorial> { TutorialScreen(navController) }
    }
}

sealed interface Screen {
    @Serializable
    data object Menu : Screen

    @Serializable
    data object Game : Screen

    @Serializable
    data object Achievements : Screen

    @Serializable
    data object Tutorial : Screen
}