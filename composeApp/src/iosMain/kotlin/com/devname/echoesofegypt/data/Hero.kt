package com.devname.echoesofegypt.data

data class Hero(
    val startHealth: Int = GameParams.HERO_START_HEALTH,
    val health: Int = startHealth,
    val minAttack: Int = GameParams.HERO_MIN_ATTACK,
    val maxAttack: Int = GameParams.HERO_MAX_ATTACK,
    val potionAmount: Int = GameParams.HERO_START_POTION_AMOUNT,
    val hasTreasure: Boolean = false
)
