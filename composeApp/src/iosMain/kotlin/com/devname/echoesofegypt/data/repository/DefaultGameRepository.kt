package com.devname.echoesofegypt.data.repository

import platform.Foundation.NSUserDefaults

class DefaultGameRepository : GameRepository {
    private val userDefaults = NSUserDefaults.standardUserDefaults()

    override fun registerMummyKill() {
        val newValue = getMummyKills() + 1
        userDefaults.setInteger(newValue.toLong(), forKey = MUMMY_KILLS_KEY)
    }

    override fun registerPotionDrink() {
        val newValue = getPotionDrinks() + 1
        userDefaults.setInteger(newValue.toLong(), forKey = POTION_DRINKS_KEY)
    }

    override fun registerDamageTaken(value: Int) {
        val newValue = getDamageTaken() + value
        userDefaults.setInteger(newValue.toLong(), forKey = DAMAGE_TAKEN_KEY)
    }

    override fun registerDamageDealt(value: Int) {
        val newValue = getDamageDealt() + value
        userDefaults.setInteger(newValue.toLong(), forKey = DAMAGE_DEALT_KEY)
    }

    override fun registerPotionPickup() {
        val newValue = getPotionPickups() + 1
        userDefaults.setInteger(newValue.toLong(), forKey = POTION_PICKUPS_KEY)
    }

    override fun registerTreasurePickup() {
        val newValue = getTreasurePickups() + 1
        userDefaults.setInteger(newValue.toLong(), forKey = TREASURE_PICKUPS_KEY)
    }

    override fun registerCompletedLvl(value: Int) {
        val newValue = getCompletedLvls() + 1
        userDefaults.setInteger(newValue.toLong(), forKey = COMPLETED_LVLS_KEY)
        val newMaxCompletedLvl = maxOf(getMaxCompletedLvl(), value)
        userDefaults.setInteger(newMaxCompletedLvl.toLong(), forKey = MAX_COMPLETED_LVL_KEY)
    }

    override fun registerCompletedLvlWithoutKills() {
        val newValue = getCompletedLvlsWithoutKills() + 1
        userDefaults.setInteger(newValue.toLong(), forKey = COMPLETED_LVLS_WITHOUT_KILLS_KEY)
    }

    override fun getMummyKills(): Int {
        return if (userDefaults.objectForKey(MUMMY_KILLS_KEY) != null) {
            userDefaults.integerForKey(MUMMY_KILLS_KEY).toInt()
        } else 0
    }

    override fun getPotionDrinks(): Int {
        return if (userDefaults.objectForKey(POTION_DRINKS_KEY) != null) {
            userDefaults.integerForKey(POTION_DRINKS_KEY).toInt()
        } else 0
    }

    override fun getDamageTaken(): Int {
        return if (userDefaults.objectForKey(DAMAGE_TAKEN_KEY) != null) {
            userDefaults.integerForKey(DAMAGE_TAKEN_KEY).toInt()
        } else 0
    }

    override fun getDamageDealt(): Int {
        return if (userDefaults.objectForKey(DAMAGE_DEALT_KEY) != null) {
            userDefaults.integerForKey(DAMAGE_DEALT_KEY).toInt()
        } else 0
    }

    override fun getPotionPickups(): Int {
        return if (userDefaults.objectForKey(POTION_PICKUPS_KEY) != null) {
            userDefaults.integerForKey(POTION_PICKUPS_KEY).toInt()
        } else 0
    }

    override fun getTreasurePickups(): Int {
        return if (userDefaults.objectForKey(TREASURE_PICKUPS_KEY) != null) {
            userDefaults.integerForKey(TREASURE_PICKUPS_KEY).toInt()
        } else 0
    }

    override fun getCompletedLvls(): Int {
        return if (userDefaults.objectForKey(COMPLETED_LVLS_KEY) != null) {
            userDefaults.integerForKey(COMPLETED_LVLS_KEY).toInt()
        } else 0
    }

    override fun getCompletedLvlsWithoutKills(): Int {
        return if (userDefaults.objectForKey(COMPLETED_LVLS_WITHOUT_KILLS_KEY) != null) {
            userDefaults.integerForKey(COMPLETED_LVLS_WITHOUT_KILLS_KEY).toInt()
        } else 0
    }

    override fun getMaxCompletedLvl(): Int {
        return if (userDefaults.objectForKey(MAX_COMPLETED_LVL_KEY) != null) {
            userDefaults.integerForKey(MAX_COMPLETED_LVL_KEY).toInt()
        } else 0
    }

    override fun setMusic(value: Int) {
        userDefaults.setInteger(value.toLong(), forKey = MUSIC_KEY)
    }

    override fun setSounds(value: Int) {
        userDefaults.setInteger(value.toLong(), forKey = SOUNDS_KEY)
    }

    override fun getMusic(): Int {
        return if (userDefaults.objectForKey(MUSIC_KEY) != null) {
            userDefaults.integerForKey(MUSIC_KEY).toInt()
        } else GameRepository.MAX_SOUND_VALUE / 2
    }

    override fun getSounds(): Int {
        return if (userDefaults.objectForKey(SOUNDS_KEY) != null) {
            userDefaults.integerForKey(SOUNDS_KEY).toInt()
        } else GameRepository.MAX_SOUND_VALUE / 2
    }

    companion object {
        private const val MUMMY_KILLS_KEY = "mummy_kills"
        private const val POTION_DRINKS_KEY = "potion_drinks"
        private const val DAMAGE_TAKEN_KEY = "damage_taken"
        private const val DAMAGE_DEALT_KEY = "damage_dealt"
        private const val POTION_PICKUPS_KEY = "potion_pickups"
        private const val TREASURE_PICKUPS_KEY = "treasure_pickups"
        private const val COMPLETED_LVLS_KEY = "completed_lvls"
        private const val COMPLETED_LVLS_WITHOUT_KILLS_KEY = "completed_lvls_without_kills"
        private const val MAX_COMPLETED_LVL_KEY = "max_completed_lvl"

        private const val MUSIC_KEY = "music"
        private const val SOUNDS_KEY = "sounds"
    }
}