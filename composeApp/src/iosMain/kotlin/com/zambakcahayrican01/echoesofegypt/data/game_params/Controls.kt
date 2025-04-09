package com.zambakcahayrican01.echoesofegypt.data.game_params

data class Controls(
    val onMoveUp: () -> Unit,
    val onMoveDown: () -> Unit,
    val onMoveLeft: () -> Unit,
    val onMoveRight: () -> Unit,
    val onAttackUp: () -> Unit,
    val onAttackDown: () -> Unit,
    val onAttackLeft: () -> Unit,
    val onAttackRight: () -> Unit,
    val onDrinkPotion: () -> Unit,
)