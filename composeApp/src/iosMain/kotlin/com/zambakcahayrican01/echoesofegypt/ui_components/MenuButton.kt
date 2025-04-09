package com.zambakcahayrican01.echoesofegypt.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

@Composable
fun MenuButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    description: String,
    painter: Painter,
) {
    Image(
        modifier = modifier.clip(CircleShape).clickable { onClick() },
        painter = painter,
        contentDescription = description,
        contentScale = ContentScale.FillBounds
    )
}