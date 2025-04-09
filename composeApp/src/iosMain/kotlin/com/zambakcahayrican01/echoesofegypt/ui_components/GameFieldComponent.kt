package com.zambakcahayrican01.echoesofegypt.ui_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zambakcahayrican01.echoesofegypt.data.game_params.Cell

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