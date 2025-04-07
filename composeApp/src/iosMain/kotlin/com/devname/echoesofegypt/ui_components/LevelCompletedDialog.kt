package com.devname.echoesofegypt.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.devname.echoesofegypt.data.game_params.LevelGenerator
import com.devname.echoesofegypt.utils.UiConfig
import echoesofegypt.composeapp.generated.resources.Res
import echoesofegypt.composeapp.generated.resources.bg_dialog
import echoesofegypt.composeapp.generated.resources.completed
import echoesofegypt.composeapp.generated.resources.game_completed
import echoesofegypt.composeapp.generated.resources.green_button
import echoesofegypt.composeapp.generated.resources.hero
import echoesofegypt.composeapp.generated.resources.hero_full
import echoesofegypt.composeapp.generated.resources.level
import echoesofegypt.composeapp.generated.resources.next
import echoesofegypt.composeapp.generated.resources.restart
import echoesofegypt.composeapp.generated.resources.swords
import echoesofegypt.composeapp.generated.resources.treasure
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun LevelCompletedDialog(
    modifier: Modifier = Modifier,
    onNext: () -> Unit,
    onRestart: () -> Unit,
    level: Int,
) {
    Dialog(onDismissRequest = {}) {
        Box(modifier.fillMaxSize()) {
            Box(
                Modifier.paint(
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
                                text = "${stringResource(Res.string.level)} $level",
                                color = Color(0xff400000),
                                fontSize = 22.sp
                            )
                            if (level > LevelGenerator.maxLvl) {
                                AppText(
                                    text = stringResource(Res.string.completed),
                                    color = Color(0xff400000),
                                    fontSize = 27.sp
                                )
                            } else {
                                AppText(
                                    text = stringResource(Res.string.game_completed),
                                    color = Color(0xff400000),
                                    fontSize = 17.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        Image(
                            modifier = Modifier.height(UiConfig.dialogImageHeight),
                            painter = painterResource(Res.drawable.swords),
                            contentDescription = stringResource(Res.string.treasure),
                            contentScale = ContentScale.FillHeight
                        )
                        if (level < LevelGenerator.maxLvl) {
                            Box(
                                Modifier.width(150.dp).paint(
                                    painter = painterResource(Res.drawable.green_button),
                                    contentScale = ContentScale.FillWidth
                                ).clip(RoundedCornerShape(40)).clickable { onNext() },
                                contentAlignment = Alignment.Center
                            ) {
                                AppText(
                                    text = stringResource(Res.string.next),
                                    color = Color(0xff003609),
                                    fontSize = 22.sp
                                )
                            }
                        } else {
                            Box(
                                Modifier.width(150.dp).paint(
                                    painter = painterResource(Res.drawable.green_button),
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
            }
            Image(
                modifier = Modifier.align(Alignment.BottomEnd).fillMaxWidth(0.5f),
                painter = painterResource(Res.drawable.hero_full),
                contentDescription = stringResource(Res.string.hero),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}
