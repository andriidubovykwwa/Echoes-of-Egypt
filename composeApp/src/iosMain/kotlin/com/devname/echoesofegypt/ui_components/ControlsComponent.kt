package com.devname.echoesofegypt.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.devname.echoesofegypt.data.game_params.Controls
import com.devname.echoesofegypt.utils.UiConfig
import echoesofegypt.composeapp.generated.resources.Res
import echoesofegypt.composeapp.generated.resources.arrow
import echoesofegypt.composeapp.generated.resources.blue_circle
import echoesofegypt.composeapp.generated.resources.health
import echoesofegypt.composeapp.generated.resources.heart
import echoesofegypt.composeapp.generated.resources.left
import echoesofegypt.composeapp.generated.resources.potion
import echoesofegypt.composeapp.generated.resources.red_circle
import echoesofegypt.composeapp.generated.resources.right
import echoesofegypt.composeapp.generated.resources.swords
import echoesofegypt.composeapp.generated.resources.treasure
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ControlsComponent(
    modifier: Modifier = Modifier,
    controls: Controls,
    canMoveUp: Boolean,
    canMoveDown: Boolean,
    canMoveLeft: Boolean,
    canMoveRight: Boolean,
    canAttackUp: Boolean,
    canAttackDown: Boolean,
    canAttackLeft: Boolean,
    canAttackRight: Boolean,
    potionsAmount: Int,
    heroMaxHealth: Int,
    heroHealth: Int,
    hasTreasure: Boolean
) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Box(
            Modifier.size(UiConfig.controlsCircleSize).paint(
                painter = painterResource(Res.drawable.red_circle),
                contentScale = ContentScale.FillBounds
            )
        ) {
            Image(
                modifier = Modifier.align(Alignment.Center).fillMaxHeight(0.56f),
                painter = painterResource(Res.drawable.heart),
                contentDescription = stringResource(Res.string.health),
                contentScale = ContentScale.FillHeight
            )
            Box(
                modifier = Modifier.align(Alignment.BottomCenter)
                    .fillMaxWidth(0.9f)
                    .background(Color(0xCC351400), RoundedCornerShape(25))
                    .padding(vertical = 2.dp),
                contentAlignment = Alignment.Center
            ) {
                AppText(
                    text = "$heroHealth/$heroMaxHealth",
                    fontSize = UiConfig.controlsCircleTextSize,
                    lineHeight = UiConfig.controlsCircleTextSize,
                    color = Color.White
                )
            }
            if (hasTreasure) {
                Image(
                    modifier = Modifier.align(Alignment.TopStart).fillMaxWidth(0.4f),
                    painter = painterResource(Res.drawable.treasure),
                    contentDescription = stringResource(Res.string.treasure),
                    contentScale = ContentScale.FillWidth
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            ControlButton(
                modifier = Modifier.size(UiConfig.smallMenuButtonSize),
                onClick = if (canAttackLeft) controls.onAttackLeft else controls.onMoveLeft,
                enabled = canMoveLeft || canAttackLeft,
                imageRotation = if (!canAttackLeft) -90f else 0f,
                description = stringResource(Res.string.left),
                painter = painterResource(if (canAttackLeft) Res.drawable.swords else Res.drawable.arrow)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ControlButton(
                    modifier = Modifier.size(UiConfig.smallMenuButtonSize),
                    onClick = if (canAttackUp) controls.onAttackUp else controls.onMoveUp,
                    enabled = canMoveUp || canAttackUp,
                    description = stringResource(Res.string.right),
                    painter = painterResource(if (canAttackUp) Res.drawable.swords else Res.drawable.arrow)
                )
                ControlButton(
                    modifier = Modifier.size(UiConfig.smallMenuButtonSize),
                    onClick = if (canAttackDown) controls.onAttackDown else controls.onMoveDown,
                    enabled = canMoveDown || canAttackDown,
                    imageRotation = if (!canAttackDown) 180f else 0f,
                    description = stringResource(Res.string.right),
                    painter = painterResource(if (canAttackDown) Res.drawable.swords else Res.drawable.arrow)
                )
            }
            ControlButton(
                modifier = Modifier.size(UiConfig.smallMenuButtonSize),
                onClick = if (canAttackRight) controls.onAttackRight else controls.onMoveRight,
                enabled = canMoveRight || canAttackRight,
                imageRotation = if (!canAttackRight) 90f else 0f,
                description = stringResource(Res.string.right),
                painter = painterResource(if (canAttackRight) Res.drawable.swords else Res.drawable.arrow)
            )
        }
        Box(
            Modifier.size(UiConfig.controlsCircleSize).paint(
                painter = painterResource(Res.drawable.blue_circle),
                contentScale = ContentScale.FillBounds
            )
        ) {
            Image(
                modifier = Modifier.align(Alignment.Center).fillMaxHeight(0.62f)
                    .clip(RoundedCornerShape(25)).clickable { controls.onDrinkPotion() },
                painter = painterResource(Res.drawable.potion),
                contentDescription = stringResource(Res.string.potion),
                contentScale = ContentScale.FillHeight
            )
            Box(
                modifier = Modifier.align(Alignment.BottomCenter)
                    .fillMaxWidth(0.9f)
                    .background(Color(0xCC351400), RoundedCornerShape(25))
                    .padding(vertical = 2.dp),
                contentAlignment = Alignment.Center
            ) {
                AppText(
                    text = potionsAmount.toString(),
                    fontSize = UiConfig.controlsCircleTextSize,
                    lineHeight = UiConfig.controlsCircleTextSize,
                    color = Color.White
                )
            }
        }
    }
}
