package com.devname.echoesofegypt.screen.tutorial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import echoesofegypt.composeapp.generated.resources.Res
import echoesofegypt.composeapp.generated.resources.achievements
import echoesofegypt.composeapp.generated.resources.achievements_tuturial_text
import echoesofegypt.composeapp.generated.resources.gameplay
import echoesofegypt.composeapp.generated.resources.gameplay_tuturial_text
import echoesofegypt.composeapp.generated.resources.overview
import echoesofegypt.composeapp.generated.resources.overview_tuturial_text
import org.jetbrains.compose.resources.stringResource

@Composable
fun TutorialScreen(
    navController: NavController,
) {
    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = WindowInsets.safeContent.asPaddingValues(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            item {
                Button(
                    modifier = Modifier.align(Alignment.TopStart),
                    onClick = { navController.popBackStack() }) {
                    Text(text = "Back")
                }
            }
            item {
                Text(stringResource(Res.string.overview))
            }
            item {
                Text(stringResource(Res.string.overview_tuturial_text))
            }
            item {
                Text(stringResource(Res.string.gameplay))
            }
            item {
                Text(stringResource(Res.string.gameplay_tuturial_text))
            }
            item {
                Text(stringResource(Res.string.achievements))
            }
            item {
                Text(stringResource(Res.string.achievements_tuturial_text))
            }
        }
    }
}