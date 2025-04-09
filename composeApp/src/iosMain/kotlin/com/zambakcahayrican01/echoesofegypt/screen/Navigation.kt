package com.zambakcahayrican01.echoesofegypt.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.zambakcahayrican01.echoesofegypt.screen.achievements.AchievementsScreen
import com.zambakcahayrican01.echoesofegypt.screen.content.ContentScreen
import com.zambakcahayrican01.echoesofegypt.screen.game.GameScreen
import com.zambakcahayrican01.echoesofegypt.screen.loading.LoadingScreen
import com.zambakcahayrican01.echoesofegypt.screen.menu.MenuScreen
import com.zambakcahayrican01.echoesofegypt.screen.tutorial.TutorialScreen
import kotlinx.serialization.Serializable

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Screen.Loading
    ) {
        composable<Screen.Loading> { LoadingScreen(navController) }
        composable<Screen.Menu> { MenuScreen(navController) }
        composable<Screen.Game> { GameScreen(navController) }
        composable<Screen.Achievements> { AchievementsScreen(navController) }
        composable<Screen.Tutorial> { TutorialScreen(navController) }
        composable<Screen.Content> { ContentScreen(it.toRoute<Screen.Content>().content) }
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

    @Serializable
    data object Loading : Screen

    @Serializable
    data class Content(val content: String) : Screen
}