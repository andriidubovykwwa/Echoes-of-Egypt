package com.zambakcahayrican01.echoesofegypt.screen.loading

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zambakcahayrican01.echoesofegypt.screen.Screen
import com.zambakcahayrican01.echoesofegypt.ui_components.SpinnerLoader
import com.zambakcahayrican01.echoesofegypt.utils.UiConfig
import echoesofegypt.composeapp.generated.resources.Res
import echoesofegypt.composeapp.generated.resources.app_name
import echoesofegypt.composeapp.generated.resources.app_title
import echoesofegypt.composeapp.generated.resources.bg1
import echoesofegypt.composeapp.generated.resources.loading
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoadingScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(Screen.Menu)
    }
    val infiniteTransition = rememberInfiniteTransition()
    val titleRotation by infiniteTransition.animateFloat(
        initialValue = -15f,
        targetValue = 15f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        Modifier.fillMaxSize().paint(
            painter = painterResource(Res.drawable.bg1),
            contentScale = ContentScale.FillBounds
        ).safeContentPadding()
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Image(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp).graphicsLayer {
                        rotationY = titleRotation
                    },
                    painter = painterResource(Res.drawable.app_title),
                    contentDescription = stringResource(Res.string.app_name),
                    contentScale = ContentScale.FillWidth
                )
            }
            Column(
                Modifier.padding(bottom = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                SpinnerLoader(Modifier.size(35.dp))
                Image(
                    modifier = Modifier.height(UiConfig.headerTextHeight),
                    painter = painterResource(Res.drawable.loading),
                    contentDescription = stringResource(Res.string.loading),
                    contentScale = ContentScale.FillHeight
                )
            }
        }
    }
}
