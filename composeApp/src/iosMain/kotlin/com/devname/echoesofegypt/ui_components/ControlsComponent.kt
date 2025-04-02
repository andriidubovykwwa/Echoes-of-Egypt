package com.devname.echoesofegypt.ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ControlsComponent(
    modifier: Modifier = Modifier,
    onMoveUp: () -> Unit,
    onMoveDown: () -> Unit,
    onMoveLeft: () -> Unit,
    onMoveRight: () -> Unit,
    onAttackUp: () -> Unit,
    onAttackDown: () -> Unit,
    onAttackLeft: () -> Unit,
    onAttackRight: () -> Unit,
    canMoveUp: Boolean,
    canMoveDown: Boolean,
    canMoveLeft: Boolean,
    canMoveRight: Boolean,
    canAttackUp: Boolean,
    canAttackDown: Boolean,
    canAttackLeft: Boolean,
    canAttackRight: Boolean,
) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = if (canAttackLeft) onAttackLeft else onMoveLeft,
            enabled = canMoveLeft || canAttackLeft
        ) {
            Icon(
                imageVector = if (canAttackLeft) Icons.Default.Add else Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Left"
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = if (canAttackUp) onAttackUp else onMoveUp,
                enabled = canMoveUp || canAttackUp
            ) {
                Icon(
                    imageVector = if (canAttackUp) Icons.Default.Add else Icons.Default.KeyboardArrowUp,
                    contentDescription = "Up"
                )
            }
            IconButton(
                onClick = if (canAttackDown) onAttackDown else onMoveDown,
                enabled = canMoveDown || canAttackDown
            ) {
                Icon(
                    imageVector = if (canAttackDown) Icons.Default.Add else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Down"
                )
            }
        }
        IconButton(
            onClick = if (canAttackRight) onAttackRight else onMoveRight,
            enabled = canMoveRight || canAttackRight
        ) {
            Icon(
                imageVector = if (canAttackRight) Icons.Default.Add else Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Right"
            )
        }
    }
}
