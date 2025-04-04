package com.devname.echoesofegypt.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import echoesofegypt.composeapp.generated.resources.Res
import echoesofegypt.composeapp.generated.resources.control_box
import org.jetbrains.compose.resources.painterResource

@Composable
fun ControlButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    painter: Painter,
    description: String,
    enabled: Boolean,
    imageRotation: Float = 0f,
) {
    Box(
        modifier.alpha(if (enabled) 1f else 0.3f).paint(
            painter = painterResource(Res.drawable.control_box),
            contentScale = ContentScale.FillBounds
        ).clickable(enabled = enabled) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(0.5f).rotate(imageRotation),
            painter = painter,
            contentDescription = description,
            contentScale = ContentScale.FillWidth
        )
    }
}