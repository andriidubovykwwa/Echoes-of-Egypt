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
import echoesofegypt.composeapp.generated.resources.mummy
import echoesofegypt.composeapp.generated.resources.mummy_full
import echoesofegypt.composeapp.generated.resources.next
import echoesofegypt.composeapp.generated.resources.red_button
import echoesofegypt.composeapp.generated.resources.restart
import echoesofegypt.composeapp.generated.resources.skull
import echoesofegypt.composeapp.generated.resources.swords
import echoesofegypt.composeapp.generated.resources.treasure
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
            Image(
                modifier = Modifier.align(Alignment.BottomStart).fillMaxWidth(0.5f),
                painter = painterResource(Res.drawable.mummy_full),
                contentDescription = stringResource(Res.string.mummy),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}
