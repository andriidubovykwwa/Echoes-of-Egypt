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
    const val MAX_LVL = 2

    fun generateLevel(lvl: Int): List<Cell> {
        return when (lvl) {
            1 -> getLevelFromString(LVL_1)
            else -> getLevelFromString(LVL_1)
        }
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