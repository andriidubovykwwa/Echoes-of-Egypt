package com.devname.echoesofegypt.screen.game

import com.devname.echoesofegypt.data.Cell
import com.devname.echoesofegypt.data.GameParams
import com.devname.echoesofegypt.data.Hero
import com.devname.echoesofegypt.data.LevelGenerator

data class GameState(
    val fieldWidth: Int = GameParams.DEFAULT_FIELD_WIDTH,
    val gameField: List<Cell> = LevelGenerator.generateFirstLevel()
) {
    val heroLocationIndex: Int
        get() = gameField.indexOfFirst { it is Cell.HeroOccupied }

    val hero: Hero
        get() {
            val heroCell = gameField[heroLocationIndex]
            return (heroCell as Cell.HeroOccupied).hero
        }

    val canMoveUp: Boolean
        get() {
            val index = getUpNeighbor(heroLocationIndex) ?: return false
            val destinationCell = gameField[index]
            return destinationCell is Cell.Empty || destinationCell is Cell.Potion || destinationCell is Cell.Treasure || destinationCell is Cell.Exit
        }

    val canMoveDown: Boolean
        get() {
            val index = getDownNeighbor(heroLocationIndex) ?: return false
            val destinationCell = gameField[index]
            return destinationCell is Cell.Empty || destinationCell is Cell.Potion || destinationCell is Cell.Treasure || destinationCell is Cell.Exit
        }

    val canMoveLeft: Boolean
        get() {
            val index = getLeftNeighbor(heroLocationIndex) ?: return false
            val destinationCell = gameField[index]
            return destinationCell is Cell.Empty || destinationCell is Cell.Potion || destinationCell is Cell.Treasure || destinationCell is Cell.Exit
        }

    val canMoveRight: Boolean
        get() {
            val index = getRightNeighbor(heroLocationIndex) ?: return false
            val destinationCell = gameField[index]
            return destinationCell is Cell.Empty || destinationCell is Cell.Potion || destinationCell is Cell.Treasure || destinationCell is Cell.Exit
        }

    val canAttackUp: Boolean
        get() {
            val index = getUpNeighbor(heroLocationIndex) ?: return false
            val destinationCell = gameField[index]
            return destinationCell is Cell.MummyOccupied
        }

    val canAttackDown: Boolean
        get() {
            val index = getDownNeighbor(heroLocationIndex) ?: return false
            val destinationCell = gameField[index]
            return destinationCell is Cell.MummyOccupied
        }

    val canAttackLeft: Boolean
        get() {
            val index = getLeftNeighbor(heroLocationIndex) ?: return false
            val destinationCell = gameField[index]
            return destinationCell is Cell.MummyOccupied
        }

    val canAttackRight: Boolean
        get() {
            val index = getRightNeighbor(heroLocationIndex) ?: return false
            val destinationCell = gameField[index]
            return destinationCell is Cell.MummyOccupied
        }

    fun getLeftNeighbor(index: Int): Int? =
        if (index % fieldWidth > 0) index - 1 else null

    fun getRightNeighbor(index: Int): Int? =
        if (index % fieldWidth < fieldWidth - 1) index + 1 else null

    fun getUpNeighbor(index: Int): Int? =
        if (index >= fieldWidth) index - fieldWidth else null

    fun getDownNeighbor(index: Int): Int? =
        if (index + fieldWidth < gameField.size) index + fieldWidth else null

    fun areNeighbors(index1: Int, index2: Int): Boolean {
        return getUpNeighbor(index1) == index2 || getDownNeighbor(index1) == index2
                || getLeftNeighbor(index1) == index2 || getRightNeighbor(index1) == index2
    }
}
