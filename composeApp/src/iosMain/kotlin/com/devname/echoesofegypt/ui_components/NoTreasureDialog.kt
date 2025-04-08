package com.devname.echoesofegypt.ui_components

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.devname.echoesofegypt.utils.UiConfig
import echoesofegypt.composeapp.generated.resources.Res
import echoesofegypt.composeapp.generated.resources.bg_dialog
import echoesofegypt.composeapp.generated.resources.green_button
import echoesofegypt.composeapp.generated.resources.hero
import echoesofegypt.composeapp.generated.resources.hero_full
import echoesofegypt.composeapp.generated.resources.ok
import echoesofegypt.composeapp.generated.resources.treasure
import echoesofegypt.composeapp.generated.resources.you_cant_leave_without_treasure
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun NoTreasureDialog(
    modifier: Modifier = Modifier,
    onOk: () -> Unit,
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
                        AppText(
                            text = stringResource(Res.string.you_cant_leave_without_treasure),
                            color = Color(0xff400000),
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center
                        )
                        Image(
                            modifier = Modifier.height(UiConfig.dialogImageHeight),
                            painter = painterResource(Res.drawable.treasure),
                            contentDescription = stringResource(Res.string.treasure),
                            contentScale = ContentScale.FillHeight
                        )
                        Box(
                            Modifier.width(150.dp).paint(
                                painter = painterResource(Res.drawable.green_button),
                                contentScale = ContentScale.FillWidth
                            ).clip(RoundedCornerShape(40)).clickable { onOk() },
                            contentAlignment = Alignment.Center
                        ) {
                            AppText(
                                text = stringResource(Res.string.ok),
                                color = Color(0xff003609),
                                fontSize = 22.sp
                            )

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
