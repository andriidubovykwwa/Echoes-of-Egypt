package com.devname.echoesofegypt.data.repository

interface GameRepository {
    fun registerMummyKill()
    fun registerPotionDrink()
    fun registerDamageTaken(value: Int)
    fun registerDamageDealt(value: Int)
    fun registerPotionPickup()
    fun registerTreasurePickup()
    fun registerCompletedLvl(value: Int)
    fun registerCompletedLvlWithoutKills()

    fun getMummyKills(): Int
    fun getPotionDrinks(): Int
    fun getDamageTaken(): Int
    fun getDamageDealt(): Int
    fun getPotionPickups(): Int
    fun getTreasurePickups(): Int
    fun getCompletedLvls(): Int
    fun getCompletedLvlsWithoutKills(): Int

    fun getMaxCompletedLvl(): Int
}