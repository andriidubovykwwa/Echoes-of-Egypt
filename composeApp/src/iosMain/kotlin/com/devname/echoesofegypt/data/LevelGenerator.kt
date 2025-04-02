package com.devname.echoesofegypt.data

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

    fun generateFirstLevel(): List<Cell> {
        val lvlStr = LVL_1.replace("\\s".toRegex(), "") // Remove whitespaces
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