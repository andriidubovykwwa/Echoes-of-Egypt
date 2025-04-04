package com.devname.echoesofegypt.screen.tutorial

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devname.echoesofegypt.screen.Screen
import com.devname.echoesofegypt.ui_components.AppText
import com.devname.echoesofegypt.ui_components.MenuButton
import com.devname.echoesofegypt.utils.UiConfig
import echoesofegypt.composeapp.generated.resources.Res
import echoesofegypt.composeapp.generated.resources.achievements
import echoesofegypt.composeapp.generated.resources.achievements_tuturial_text
import echoesofegypt.composeapp.generated.resources.app_name
import echoesofegypt.composeapp.generated.resources.app_title
import echoesofegypt.composeapp.generated.resources.back
import echoesofegypt.composeapp.generated.resources.back_button
import echoesofegypt.composeapp.generated.resources.bg3
import echoesofegypt.composeapp.generated.resources.gameplay
import echoesofegypt.composeapp.generated.resources.gameplay_tuturial_text
import echoesofegypt.composeapp.generated.resources.overview
import echoesofegypt.composeapp.generated.resources.overview_tuturial_text
import echoesofegypt.composeapp.generated.resources.tutorial
import echoesofegypt.composeapp.generated.resources.tutorial_button
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TutorialScreen(
    navController: NavController,
) {
    Box(
        Modifier.fillMaxSize().paint(
            painter = painterResource(Res.drawable.bg3),
            contentScale = ContentScale.FillBounds
        )
    ) {
        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = WindowInsets.safeContent.asPaddingValues(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            item {
                Box(Modifier.fillMaxWidth()) {
                    MenuButton(
                        Modifier.size(UiConfig.smallMenuButtonSize).align(Alignment.CenterStart),
                        description = stringResource(Res.string.back),
                        painter = painterResource(Res.drawable.back_button),
                        onClick = { navController.popBackStack() }
                    )
                    Image(
                        modifier = Modifier.align(Alignment.Center)
                            .height(UiConfig.headerTextHeight),
                        painter = painterResource(Res.drawable.tutorial),
                        contentDescription = stringResource(Res.string.tutorial),
                        contentScale = ContentScale.FillHeight
                    )
                }
            }
            val textPadding = 15.dp
            item {
                AppText(
                    Modifier.padding(horizontal = textPadding),
                    text = stringResource(Res.string.overview)
                )
            }
            item {
                AppText(
                    Modifier.padding(horizontal = textPadding),
                    text = stringResource(Res.string.overview_tuturial_text)
                )
            }
            item {
                AppText(
                    Modifier.padding(horizontal = textPadding),
                    text = stringResource(Res.string.gameplay)
                )
            }
            item {
                AppText(
                    Modifier.padding(horizontal = textPadding),
                    text = stringResource(Res.string.gameplay_tuturial_text)
                )
            }
            item {
                AppText(
                    Modifier.padding(horizontal = textPadding),
                    text = stringResource(Res.string.achievements)
                )
            }
            item {
                AppText(
                    Modifier.padding(horizontal = textPadding),
                    text = stringResource(Res.string.achievements_tuturial_text)
                )
            }
        }
    }
}