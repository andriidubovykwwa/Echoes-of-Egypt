package com.zambakcahayrican01.echoesofegypt.data.game_params

data class Mummy(
    val maxHealth: Int = GameParams.MUMMY_MAX_HEALTH,
    val health: Int = maxHealth,
    val minAttack: Int = GameParams.MUMMY_MIN_ATTACK,
    val maxAttack: Int = GameParams.MUMMY_MAX_ATTACK,
)