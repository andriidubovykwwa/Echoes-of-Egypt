package com.devname.echoesofegypt.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devname.echoesofegypt.data.game_params.Cell

@Composable
fun GameFieldComponent(modifier: Modifier = Modifier, fieldWidth: Int, gameField: List<Cell>) {
    val fieldHeight = gameField.size / fieldWidth
    Row(modifier.aspectRatio(fieldWidth / fieldHeight.toFloat())) {
        repeat(fieldWidth) { x ->
            Column(Modifier.weight(1f)) {
                repeat(fieldHeight) { y ->
                    CellComponent(
                        Modifier.weight(1f).aspectRatio(1f),
                        cell = gameField[y * fieldWidth + x]
                    )
                }
            }
        }
    }
}