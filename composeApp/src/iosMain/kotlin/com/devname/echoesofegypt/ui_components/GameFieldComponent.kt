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
import com.devname.echoesofegypt.data.Cell

@Composable
fun GameFieldComponent(modifier: Modifier = Modifier, fieldWidth: Int, gameField: List<Cell>) {
    val fieldHeight = gameField.size / fieldWidth
    Row(modifier.aspectRatio(fieldWidth/fieldHeight.toFloat())) {
        repeat(fieldWidth) { x ->
            Column(Modifier.weight(1f)) {
                repeat(fieldHeight) { y ->
                    val i = y * fieldWidth + x
                    val cell = gameField[i]
                    Box(Modifier.weight(1f).aspectRatio(1f).padding(1.dp).background(Color.Cyan), contentAlignment = Alignment.Center) {
                        val hint = when (cell) {
                            Cell.Empty -> ""
                            Cell.Exit -> "E"
                            is Cell.HeroOccupied -> "H"
                            is Cell.MummyOccupied -> "M"
                            Cell.Potion -> "P"
                            Cell.Treasure -> "T"
                            Cell.Wall -> "W"
                        }
                       Text(text = hint, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}