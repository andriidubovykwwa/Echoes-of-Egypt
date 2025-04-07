package com.devname.echoesofegypt.data.game_params

object LevelGenerator {
    private val levels = listOf(
        """
        e - - - - - - -
        - w w - - - - -
        - w - - - w - -
        - w p t m w - -
        - w w w w w - -
        - - - h - w - -
        - - - - - w - -
        - - - - - - - p
        """, // lvl 1
        """
        p - - - w w w w
        - - m - - - - -
        - w w w w - w -
        - - - t w - w -
        w w w w w - w -
        - - - - - - w -
        p - - - h - w m
        m - - - - - w e
        """, // lvl 2
        """
        - - e w w w - m
        - m w - - - - p
        - w - - w - - w
        - w - w h w - w
        - w - - - w m w
        - t w w w w p w
        - m - - - - - w
        - - - - m - - p
        """, // lvl 3
        """
        - - - m - w w w
        - w - - p - m -
        - w w w w w - -
        - w - - p w - w
        - w m w e w - p
        - w - w w w - p
        - w - w m w w -
        h w - t - - - -
        """, // lvl 4
        """
        t w - w - - - e
        - w - - - w w w
        - - - w - - - -
        m m p w - w w -
        w w w w - m w -
        - - - w m p w -
        - w - w w w w -
        h w - - - - - -
        """, // lvl 5
    )

    val maxLvl: Int
        get() = levels.size

    fun generateLevel(
        lvl: Int,
        heroHealth: Int = GameParams.HERO_MAX_HEALTH,
        potionAmount: Int = GameParams.HERO_START_POTION_AMOUNT
    ): List<Cell> {
        if (lvl > maxLvl || lvl < 1) return getLevelFromString(
            levels.first(),
            heroHealth,
            potionAmount
        )
        return getLevelFromString(levels[lvl - 1], heroHealth, potionAmount)
    }

    private fun getLevelFromString(str: String, heroHealth: Int, potionAmount: Int): List<Cell> {
        val lvlStr = str.replace("\\s".toRegex(), "") // Remove whitespaces
        return lvlStr.map {
            when (it) {
                'w' -> Cell.Wall
                't' -> Cell.Treasure
                'e' -> Cell.Exit
                'p' -> Cell.Potion
                'm' -> Cell.MummyOccupied()
                'h' -> Cell.HeroOccupied(Hero(health = heroHealth, potionAmount = potionAmount))
                else -> Cell.Empty
            }
        }
    }
}