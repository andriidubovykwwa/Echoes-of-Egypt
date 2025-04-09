package com.zambakcahayrican01.echoesofegypt.ui_components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import echoesofegypt.composeapp.generated.resources.Res
import echoesofegypt.composeapp.generated.resources.back
import org.jetbrains.compose.resources.stringResource

@Composable
fun ContentBackButton(modifier: Modifier = Modifier, isEnabled: Boolean, onClick: () -> Unit) {
    AnimatedVisibility(
        visible = isEnabled,
        enter = slideInVertically { -it } + fadeIn() + scaleIn(initialScale = 0.8f),
        exit = slideOutVertically { it } + fadeOut() + scaleOut(targetScale = 0.8f),
    ) {
        IconButton(
            modifier = modifier
                .padding(12.dp)
                .clip(CircleShape)
                .background(Color(0x80EEEEEE)),
            onClick = onClick
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = stringResource(Res.string.back),
                tint = Color(0xFF000000)
            )
        }
    }
}