package com.devname.echoesofegypt.data.game_params

object LevelGenerator {
    private const val LVL_1 = """
        p - - - - - - -
        - - t - - w - -
        - - - m - w - -
        w - w w w w w e
        - - p w - - w w
        - m - w - m - -
        - - - - - h - -
        - - - w - - - -
    """
    private val levels = listOf(
        """
        p - - - - - - e
        - w w - t - - -
        - w - - - w - -
        - w - m - w - -
        - w w w w w - -
        - - - h - - - -
        - p - - - - - -
        - - - - - - - -
        """, // lvl 1
        """
        p - - - w w w w
        - - m - - - - -
        - w w w w - w e
        - - - t - - w -
        w w w w - - w -
        - - - - - - w -
        - p h - w - w -
        - - - - - - - -
        """, // lvl 2
        """
        p - w w w w - -
        - - - - - w e -
        w w - w - w - -
        - - m t - - - -
        - w - - - w w -
        h - - w - - - -
        - - p - - - m -
        w w w w w - - -
        """, // lvl 3
        """
        p - - - w - w w
        - w w - w - - e
        - w - - - w - -
        - - - m t - - -
        w w - w - w - -
        - - p - - - h -
        - w - w w w w -
        - - - - - - - -
        """, // lvl 4
        """
        p - w w w w - w
        - - - - - - - e
        w w - w - w w -
        - - - - - - - -
        - w m t w w - -
        h - - w - - p -
        - - - - - - - -
        w w w w w - - -
        """, // lvl 5
    )

    val maxLvl: Int
        get() = levels.size

    fun generateLevel(lvl: Int): List<Cell> {
        if (lvl > maxLvl || lvl < 1) return getLevelFromString(levels.first())
        return getLevelFromString(levels[lvl - 1])
    }

    private fun getLevelFromString(str: String): List<Cell> {
        val lvlStr = str.replace("\\s".toRegex(), "") // Remove whitespaces
        return lvlStr.map {
            when (it) {
                'w' -> Cell.Wall
                't' -> Cell.Treasure
                'e' -> Cell.Exit
                'p' -> Cell.Potion
                'm' -> Cell.MummyOccupied()
                'h' -> Cell.HeroOccupied()
                else -> Cell.Empty
            }
        }
    }
}