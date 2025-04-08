package com.devname.echoesofegypt.ui_components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun SpinnerLoader(
    modifier: Modifier = Modifier,
    lineCount: Int = 8,
    animationDuration: Int = 1200,
    lineWidth: Dp = 6.dp,
    color: Color = Color(0xff380404)
) {
    val infiniteTransition = rememberInfiniteTransition()
    val alphas = List(lineCount) { index ->
        infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 0.3f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = animationDuration
                    1f at 0
                    0.2f at animationDuration / 2
                    1f at animationDuration
                },
                repeatMode = RepeatMode.Restart,
                initialStartOffset = StartOffset(index * (animationDuration / lineCount))
            )
        )
    }

    Canvas(modifier) {
        val radius = size.minDimension / 2.5f
        val lineLength = size.minDimension / 6
        val center = size.center

        repeat(lineCount) { i ->
            val angle = (2 * 3.141592 / lineCount * i).toFloat()
            val lineStart = Offset(
                x = center.x + radius * cos(angle),
                y = center.y + radius * sin(angle)
            )
            val lineEnd = Offset(
                x = center.x + (radius + lineLength) * cos(angle),
                y = center.y + (radius + lineLength) * sin(angle)
            )
            drawLine(
                color = color,
                start = lineStart,
                end = lineEnd,
                strokeWidth = lineWidth.toPx(),
                alpha = alphas[i].value,
                cap = StrokeCap.Round
            )
        }
    }
}

