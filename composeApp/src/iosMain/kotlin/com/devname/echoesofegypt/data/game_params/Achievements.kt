package com.devname.echoesofegypt.data.game_params

enum class Achievement(val type: AchievementType, val target: Int) {
    MUMMY_KILLS_1(AchievementType.MUMMY_KILLS, 10),
    MUMMY_KILLS_2(AchievementType.MUMMY_KILLS, 50),
    MUMMY_KILLS_3(AchievementType.MUMMY_KILLS, 100),

    POTION_DRINKS_1(AchievementType.POTION_DRINKS, 3),
    POTION_DRINKS_2(AchievementType.POTION_DRINKS, 10),
    POTION_DRINKS_3(AchievementType.POTION_DRINKS, 25),

    DAMAGE_TAKEN_1(AchievementType.DAMAGE_TAKEN, 300),
    DAMAGE_TAKEN_2(AchievementType.DAMAGE_TAKEN, 1000),
    DAMAGE_TAKEN_3(AchievementType.DAMAGE_TAKEN, 2500),

    DAMAGE_DEALT_1(AchievementType.DAMAGE_DEALT, 500),
    DAMAGE_DEALT_2(AchievementType.DAMAGE_DEALT, 2000),
    DAMAGE_DEALT_3(AchievementType.DAMAGE_DEALT, 5000),

    POTION_PICKUPS_1(AchievementType.POTION_PICKUPS, 10),
    POTION_PICKUPS_2(AchievementType.POTION_PICKUPS, 25),
    POTION_PICKUPS_3(AchievementType.POTION_PICKUPS, 50),

    TREASURE_PICKUPS_1(AchievementType.TREASURE_PICKUPS, 3),
    TREASURE_PICKUPS_2(AchievementType.TREASURE_PICKUPS, 10),
    TREASURE_PICKUPS_3(AchievementType.TREASURE_PICKUPS, 20),

    COMPLETED_LVLS_1(AchievementType.COMPLETED_LVLS, 5),
    COMPLETED_LVLS_2(AchievementType.COMPLETED_LVLS, 20),
    COMPLETED_LVLS_3(AchievementType.COMPLETED_LVLS, 75),

    COMPLETED_LVLS_WITHOUT_KILLS_1(AchievementType.COMPLETED_LVLS_WITHOUT_KILLS, 1),
    COMPLETED_LVLS_WITHOUT_KILLS_2(AchievementType.COMPLETED_LVLS_WITHOUT_KILLS, 3),
    COMPLETED_LVLS_WITHOUT_KILLS_3(AchievementType.COMPLETED_LVLS_WITHOUT_KILLS, 10),

    MAX_COMPLETED_LEVEL_1(AchievementType.MAX_COMPLETED_LVL, 1),
    MAX_COMPLETED_LEVEL_2(AchievementType.MAX_COMPLETED_LVL, 3),
    MAX_COMPLETED_LEVEL_3(AchievementType.MAX_COMPLETED_LVL, 5),
}

enum class AchievementType(private val template: String) {
    MUMMY_KILLS("Kill %d mummies"),
    POTION_DRINKS("Drink %d potion"),
    DAMAGE_TAKEN("Deal %d damage"),
    DAMAGE_DEALT("Take %d damage"),
    POTION_PICKUPS("Pickup %d potion"),
    TREASURE_PICKUPS("Pickup %d treasure"),
    COMPLETED_LVLS("Complete %d levels"),
    COMPLETED_LVLS_WITHOUT_KILLS("Complete %d levels without killing any mummies"),
    MAX_COMPLETED_LVL("Complete level %d");

    fun getMessage(value: Int): String {
        return template.replace("%d", value.toString())
    }
}