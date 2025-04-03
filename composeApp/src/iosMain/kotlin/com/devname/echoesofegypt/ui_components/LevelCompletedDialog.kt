package com.devname.echoesofegypt.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.devname.echoesofegypt.data.game_params.LevelGenerator


@Composable
fun LevelCompletedDialog(
    modifier: Modifier = Modifier,
    onNext: () -> Unit,
    onRestart: () -> Unit,
    level: Int,
) {
    Dialog(onDismissRequest = {}) {
        Box(modifier.background(Color.Yellow), contentAlignment = Alignment.Center) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (level < LevelGenerator.maxLvl) {
                    Text(text = "You completed level $level")
                    Button(onClick = onNext) {
                        Text(text = "Next")
                    }
                } else {
                    Text(text = "You completed all available levels")
                    Button(onClick = onRestart) {
                        Text(text = "Restart")
                    }
                }
            }
        }
    }
}
