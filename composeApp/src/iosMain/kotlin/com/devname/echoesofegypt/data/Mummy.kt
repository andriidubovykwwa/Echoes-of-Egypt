package com.devname.echoesofegypt.data

data class Mummy(
    val startHealth: Int = GameParams.MUMMY_START_HEALTH,
    val health: Int = startHealth,
    val minAttack: Int = GameParams.MUMMY_MIN_ATTACK,
    val maxAttack: Int = GameParams.MUMMY_MAX_ATTACK,
)
