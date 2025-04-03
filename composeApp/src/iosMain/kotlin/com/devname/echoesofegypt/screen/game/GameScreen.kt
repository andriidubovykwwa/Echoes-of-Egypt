package com.devname.echoesofegypt.screen.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devname.echoesofegypt.ui_components.ControlsComponent
import com.devname.echoesofegypt.ui_components.DeathDialog
import com.devname.echoesofegypt.ui_components.GameFieldComponent
import com.devname.echoesofegypt.ui_components.LevelCompletedDialog
import com.devname.echoesofegypt.ui_components.NoTreasureDialog
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameScreen(viewModel: GameViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    Box(Modifier.fillMaxSize().safeContentPadding().background(Color.White)) {
        val text =
            "Treasure: ${state.hero.hasTreasure}"
        Text(modifier = Modifier.align(Alignment.TopCenter), text = text, fontSize = 14.sp)
        GameFieldComponent(
            Modifier.align(Alignment.Center).fillMaxWidth().padding(5.dp),
            fieldWidth = state.fieldWidth,
            gameField = state.gameField
        )
        ControlsComponent(
            Modifier.align(Alignment.BottomCenter).fillMaxWidth(),
            controls = viewModel.controls,
            canMoveUp = state.canMoveUp,
            canMoveDown = state.canMoveDown,
            canMoveLeft = state.canMoveLeft,
            canMoveRight = state.canMoveRight,
            canAttackUp = state.canAttackUp,
            canAttackDown = state.canAttackDown,
            canAttackLeft = state.canAttackLeft,
            canAttackRight = state.canAttackRight,
            potionsAmount = state.hero.potionAmount,
            heroMaxHealth = state.hero.maxHealth,
            heroHealth = state.hero.health
        )
    }
    when (state.activeDialog) {
        GameState.Dialog.LEVEL_COMPLETED -> LevelCompletedDialog(
            onNext = viewModel::nextLevel,
            onRestart = viewModel::restart,
            level = state.level
        )

        GameState.Dialog.DEATH -> DeathDialog(onRestart = viewModel::restart)
        GameState.Dialog.NO_TREASURE -> NoTreasureDialog(onOk = viewModel::closeDialog)
        null -> {}
    }
}