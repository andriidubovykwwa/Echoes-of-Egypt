package com.zambakcahayrican01.echoesofegypt.screen.game

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.zambakcahayrican01.echoesofegypt.ui_components.AppText
import com.zambakcahayrican01.echoesofegypt.ui_components.ControlsComponent
import com.zambakcahayrican01.echoesofegypt.ui_components.DeathDialog
import com.zambakcahayrican01.echoesofegypt.ui_components.GameFieldComponent
import com.zambakcahayrican01.echoesofegypt.ui_components.LevelCompletedDialog
import com.zambakcahayrican01.echoesofegypt.ui_components.MenuButton
import com.zambakcahayrican01.echoesofegypt.ui_components.NoTreasureDialog
import com.zambakcahayrican01.echoesofegypt.utils.UiConfig
import echoesofegypt.composeapp.generated.resources.Res
import echoesofegypt.composeapp.generated.resources.back
import echoesofegypt.composeapp.generated.resources.back_button
import echoesofegypt.composeapp.generated.resources.bg4
import echoesofegypt.composeapp.generated.resources.level
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameScreen(navController: NavController, viewModel: GameViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    Box(
        Modifier.fillMaxSize().paint(
            painter = painterResource(Res.drawable.bg4),
            contentScale = ContentScale.FillBounds
        ).safeContentPadding()
    ) {
        Box(Modifier.fillMaxWidth().align(Alignment.TopCenter)) {
            MenuButton(
                Modifier.size(UiConfig.smallMenuButtonSize).align(Alignment.CenterStart),
                description = stringResource(Res.string.back),
                painter = painterResource(Res.drawable.back_button),
                onClick = { navController.popBackStack() }
            )
            AppText(
                modifier = Modifier.align(Alignment.Center),
                text = "${stringResource(Res.string.level)} ${state.level}",
                fontSize = 30.sp,
                color = Color(0xffFFDA00)
            )
        }
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
            heroHealth = state.hero.health,
            hasTreasure = state.hero.hasTreasure
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