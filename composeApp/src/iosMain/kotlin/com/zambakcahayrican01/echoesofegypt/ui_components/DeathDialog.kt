package com.zambakcahayrican01.echoesofegypt.ui_components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.zambakcahayrican01.echoesofegypt.utils.UiConfig
import echoesofegypt.composeapp.generated.resources.Res
import echoesofegypt.composeapp.generated.resources.bg_dialog
import echoesofegypt.composeapp.generated.resources.mummy
import echoesofegypt.composeapp.generated.resources.mummy_full
import echoesofegypt.composeapp.generated.resources.red_button
import echoesofegypt.composeapp.generated.resources.restart
import echoesofegypt.composeapp.generated.resources.skull
import echoesofegypt.composeapp.generated.resources.you_have_died
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun DeathDialog(
    modifier: Modifier = Modifier,
    onRestart: () -> Unit,
) {
    Dialog(onDismissRequest = {}) {
        Box(modifier.fillMaxSize()) {
            Box(
                Modifier.align(Alignment.Center).paint(
                    painter = painterResource(Res.drawable.bg_dialog),
                    contentScale = ContentScale.FillBounds
                ).aspectRatio(0.805f).width(IntrinsicSize.Min),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    Modifier.fillMaxWidth(0.54f).fillMaxHeight(0.52f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        Modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AppText(
                                text = stringResource(Res.string.you_have_died),
                                color = Color(0xff400000),
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                        Image(
                            modifier = Modifier.height(UiConfig.dialogImageHeight),
                            painter = painterResource(Res.drawable.skull),
                            contentDescription = stringResource(Res.string.skull),
                            contentScale = ContentScale.FillHeight
                        )
                        Box(
                            Modifier.width(150.dp).paint(
                                painter = painterResource(Res.drawable.red_button),
                                contentScale = ContentScale.FillWidth
                            ).clip(RoundedCornerShape(40)).clickable { onRestart() },
                            contentAlignment = Alignment.Center
                        ) {
                            AppText(
                                text = stringResource(Res.string.restart),
                                color = Color(0xff003609),
                                fontSize = 22.sp
                            )
                        }
                    }
                }
            }
            var isCharacterVisible by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) { isCharacterVisible = true }
            AnimatedVisibility(
                visible = isCharacterVisible,
                enter = slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(400)
                ),
                exit = fadeOut()
            ) {
                Box(Modifier.fillMaxSize()) {
                    Image(
                        modifier = Modifier.align(Alignment.BottomStart).offset(y = 50.dp)
                            .fillMaxWidth(0.5f),
                        painter = painterResource(Res.drawable.mummy_full),
                        contentDescription = stringResource(Res.string.mummy),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
        }
    }
}
