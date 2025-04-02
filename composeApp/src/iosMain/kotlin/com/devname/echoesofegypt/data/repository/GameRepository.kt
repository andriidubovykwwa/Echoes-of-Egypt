package com.devname.echoesofegypt.data.repository

interface GameRepository {
    fun registerMummyKill()
    fun registerPotionDrink()
    fun registerDamageTaken(value: Int)
    fun registerDamageDealt(value: Int)
    fun registerPotionPickup()
    fun registerTreasurePickup()
}