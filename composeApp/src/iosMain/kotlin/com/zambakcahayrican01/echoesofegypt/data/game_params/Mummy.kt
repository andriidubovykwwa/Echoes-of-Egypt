package com.zambakcahayrican01.echoesofegypt.data.game_params

import echoesofegypt.composeapp.generated.resources.Res
import echoesofegypt.composeapp.generated.resources.mummy
import echoesofegypt.composeapp.generated.resources.mummy2
import org.jetbrains.compose.resources.DrawableResource

data class Mummy(
    val maxHealth: Int = GameParams.MUMMY_MAX_HEALTH,
    val health: Int = maxHealth,
    val minAttack: Int = GameParams.MUMMY_MIN_ATTACK,
    val maxAttack: Int = GameParams.MUMMY_MAX_ATTACK,
    val drawableRes: DrawableResource = listOf(Res.drawable.mummy, Res.drawable.mummy2).random()
)