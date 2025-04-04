package com.devname.echoesofegypt.screen.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devname.echoesofegypt.screen.Screen
import com.devname.echoesofegypt.ui_components.MenuButton
import com.devname.echoesofegypt.utils.OrientationManager
import com.devname.echoesofegypt.utils.UiConfig
import echoesofegypt.composeapp.generated.resources.Res
import echoesofegypt.composeapp.generated.resources.achievements
import echoesofegypt.composeapp.generated.resources.achievements_button
import echoesofegypt.composeapp.generated.resources.app_name
import echoesofegypt.composeapp.generated.resources.app_title
import echoesofegypt.composeapp.generated.resources.bg2
import echoesofegypt.composeapp.generated.resources.play
import echoesofegypt.composeapp.generated.resources.play_button
import echoesofegypt.composeapp.generated.resources.settings
import echoesofegypt.composeapp.generated.resources.settings_button
import echoesofegypt.composeapp.generated.resources.tutorial
import echoesofegypt.composeapp.generated.resources.tutorial_button
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MenuScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        OrientationManager().orientation = OrientationManager.Orientation.PORTRAIT
    }
    Box(
        Modifier.fillMaxSize().paint(
            painter = painterResource(Res.drawable.bg2),
            contentScale = ContentScale.FillBounds
        ).safeContentPadding()
    ) {
        Column(
            Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                painter = painterResource(Res.drawable.app_title),
                contentDescription = stringResource(Res.string.app_name),
                contentScale = ContentScale.FillWidth
            )
            MenuButton(
                Modifier.size(UiConfig.bigMenuButtonSize),
                description = stringResource(Res.string.play),
                painter = painterResource(Res.drawable.play_button),
                onClick = { navController.navigate(Screen.Game) }
            )
        }
        Column(
            Modifier.align(Alignment.TopEnd),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MenuButton(
                Modifier.size(UiConfig.smallMenuButtonSize),
                description = stringResource(Res.string.achievements),
                painter = painterResource(Res.drawable.achievements_button),
                onClick = { navController.navigate(Screen.Achievements) }
            )
            MenuButton(
                Modifier.size(UiConfig.smallMenuButtonSize),
                description = stringResource(Res.string.tutorial),
                painter = painterResource(Res.drawable.tutorial_button),
                onClick = { navController.navigate(Screen.Tutorial) }
            )
            MenuButton(
                Modifier.size(UiConfig.smallMenuButtonSize),
                description = stringResource(Res.string.settings),
                painter = painterResource(Res.drawable.settings_button),
                onClick = { /* TODO */ }
            )
        }
    }
}
