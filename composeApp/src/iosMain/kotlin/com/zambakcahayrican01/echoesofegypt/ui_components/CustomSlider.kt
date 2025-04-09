package com.zambakcahayrican01.echoesofegypt.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import echoesofegypt.composeapp.generated.resources.Res
import echoesofegypt.composeapp.generated.resources.thumb
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlin.math.roundToInt

@Composable
fun CustomSlider(
    modifier: Modifier = Modifier,
    value: Int,
    maxValue: Int,
    brush: Brush,
    onValueChange: (Int) -> Unit,
) {

    var sliderWidthPx by remember { mutableStateOf(0f) }
    var thumbWidthPx by remember { mutableStateOf(0f) }
    var thumbPosition by remember { mutableStateOf(value / maxValue.toFloat()) }
    val roundCorner = 20.dp

    Box(
        modifier
            .background(Color(0xffBE9228), RoundedCornerShape(roundCorner))
            .border(1.dp, Color(0xffA77B18), RoundedCornerShape(roundCorner))
            .clip(RoundedCornerShape(roundCorner))
            .onGloballyPositioned {
                sliderWidthPx = it.size.width.toFloat()
            }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    thumbPosition = (thumbPosition + dragAmount.x / sliderWidthPx).coerceIn(0f, 1f)
                    onValueChange((thumbPosition * maxValue).roundToInt())
                    change.consume()
                }
            }
    ) {
        // Fill
        Box(
            Modifier.fillMaxWidth(thumbPosition).fillMaxHeight().background(
                brush,
                RoundedCornerShape(roundCorner, 0.dp, 0.dp, roundCorner)
            )
        )
        // Thumb
        Image(
            painter = painterResource(Res.drawable.thumb),
            modifier = Modifier.fillMaxHeight()
                .scale(1.05f)
                .onGloballyPositioned { thumbWidthPx = it.size.width.toFloat() }.offset {
                    IntOffset(
                        (thumbPosition * (sliderWidthPx - thumbWidthPx * 1.5)).toInt(),
                        0
                    )
                }.align(Alignment.CenterStart),
            contentScale = ContentScale.FillHeight,
            contentDescription = stringResource(Res.string.thumb)
        )
    }
}
