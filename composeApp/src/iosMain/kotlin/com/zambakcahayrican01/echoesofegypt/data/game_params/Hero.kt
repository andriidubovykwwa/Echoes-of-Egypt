package com.zambakcahayrican01.echoesofegypt.data.game_params

data class Hero(
    val maxHealth: Int = GameParams.HERO_MAX_HEALTH,
    val health: Int = maxHealth,
    val minAttack: Int = GameParams.HERO_MIN_ATTACK,
    val maxAttack: Int = GameParams.HERO_MAX_ATTACK,
    val potionAmount: Int = GameParams.HERO_START_POTION_AMOUNT,
    val hasTreasure: Boolean = false
) {
    val canDrinkPotion: Boolean
        get() = potionAmount > 0 && health < maxHealth
}