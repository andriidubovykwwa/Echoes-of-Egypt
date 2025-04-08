package com.devname.echoesofegypt.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.devname.echoesofegypt.data.repository.GameRepository
import com.devname.echoesofegypt.utils.UiConfig
import echoesofegypt.composeapp.generated.resources.Res
import echoesofegypt.composeapp.generated.resources.bg_settings
import echoesofegypt.composeapp.generated.resources.green_button
import echoesofegypt.composeapp.generated.resources.music
import echoesofegypt.composeapp.generated.resources.save
import echoesofegypt.composeapp.generated.resources.settings
import echoesofegypt.composeapp.generated.resources.sounds
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingsDialog(
    modifier: Modifier = Modifier,
    onSave: () -> Unit,
    onChangeMusic: (Int) -> Unit,
    onChangeSounds: (Int) -> Unit,
    music: Int,
    sounds: Int,
) {
    Dialog(onDismissRequest = {}) {
        Box(Modifier.widthIn(min = 350.dp).heightIn(min = 350.dp)) {
            Column(
                modifier.paint(
                    painter = painterResource(Res.drawable.bg_settings),
                    contentScale = ContentScale.FillWidth
                ).padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.height(UiConfig.headerTextHeight),
                    painter = painterResource(Res.drawable.settings),
                    contentDescription = stringResource(Res.string.settings),
                    contentScale = ContentScale.FillHeight
                )
                Spacer(Modifier.height(15.dp))
                Row(
                    Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(Modifier.weight(4f)) {
                        AppText(text = stringResource(Res.string.music))
                    }
                    CustomSlider(
                        value = music,
                        onValueChange = onChangeMusic,
                        maxValue = GameRepository.MAX_SOUND_VALUE,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xffF023FF),
                                Color(0xff601599),
                            )
                        ),
                        modifier = Modifier
                            .weight(6f)
                            .height(25.dp)
                    )
                }
                Spacer(Modifier.height(10.dp))
                Row(
                    Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(Modifier.weight(4f)) {
                        AppText(text = stringResource(Res.string.sounds))
                    }
                    CustomSlider(
                        value = sounds,
                        onValueChange = onChangeSounds,
                        maxValue = GameRepository.MAX_SOUND_VALUE,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xff23CFFF),
                                Color(0xff152B99),
                            )
                        ),
                        modifier = Modifier
                            .weight(6f)
                            .height(25.dp)
                    )
                }
                Spacer(Modifier.height(15.dp))
                Box(
                    Modifier.width(150.dp).paint(
                        painter = painterResource(Res.drawable.green_button),
                        contentScale = ContentScale.FillWidth
                    ).clip(RoundedCornerShape(40)).clickable { onSave() },
                    contentAlignment = Alignment.Center
                ) {
                    AppText(
                        text = stringResource(Res.string.save),
                        color = Color(0xff003609),
                        fontSize = 22.sp
                    )
                }
            }
        }
    }
}