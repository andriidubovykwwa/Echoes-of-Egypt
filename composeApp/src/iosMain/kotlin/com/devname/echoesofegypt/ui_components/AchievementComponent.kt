package com.devname.echoesofegypt.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.devname.echoesofegypt.data.game_params.Achievement
import echoesofegypt.composeapp.generated.resources.Res
import echoesofegypt.composeapp.generated.resources.completed
import echoesofegypt.composeapp.generated.resources.star
import echoesofegypt.composeapp.generated.resources.star_outline
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AchievementComponent(
    modifier: Modifier = Modifier,
    achievement: Achievement,
    currentValue: Int
) {
    val displayedValue = if (currentValue < achievement.target) currentValue
    else achievement.target
    val isCompleted = displayedValue >= achievement.target
    val shape = RoundedCornerShape(25)
    val backgroundColor = if (isCompleted) Color(0xff0055DD) else Color(0xff002E79)
    var text = achievement.type.getMessage(achievement.target)
    text += if (isCompleted) " (${stringResource(Res.string.completed)})"
    else " ($displayedValue/${achievement.target})"
    Row(
        modifier = modifier
            .border(2.dp, Color(0xff0079E9), shape)
            .background(backgroundColor, shape)
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Image(
            modifier = Modifier.size(50.dp),
            painter = painterResource(if (isCompleted) Res.drawable.star else Res.drawable.star_outline),
            contentDescription = stringResource(if (isCompleted) Res.string.star else Res.string.star_outline)
        )
        AppText(text = text, color = Color(0xffffffff), textAlign = TextAlign.Center)
    }
}