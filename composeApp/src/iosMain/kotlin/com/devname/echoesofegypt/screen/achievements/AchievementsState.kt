package com.devname.echoesofegypt.screen.achievements

data class AchievementsState(
    val mummyKills: Int = 0,
    val potionDrinks: Int = 0,
    val damageTaken: Int = 0,
    val damageDealt: Int = 0,
    val potionPickups: Int = 0,
    val treasurePickups: Int = 0,
    val completedLvls: Int = 0,
    val completedLvlsWithoutKills: Int = 0,
    val maxCompletedLvl: Int = 0,
)
