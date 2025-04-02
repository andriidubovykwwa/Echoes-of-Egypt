package com.devname.echoesofegypt.screen.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devname.echoesofegypt.data.Cell
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val _state = MutableStateFlow(GameState())
    val state = _state.asStateFlow()

    fun attackLeft() = viewModelScope.launch {
        attack(
            canAttack = state.value.canAttackLeft,
            onGetNeighbor = state.value::getLeftNeighbor
        )
    }

    fun attackRight() = viewModelScope.launch {
        attack(
            canAttack = state.value.canAttackRight,
            onGetNeighbor = state.value::getRightNeighbor
        )
    }

    fun attackUp() = viewModelScope.launch {
        attack(
            canAttack = state.value.canAttackUp,
            onGetNeighbor = state.value::getUpNeighbor
        )
    }

    fun attackDown() = viewModelScope.launch {
        attack(
            canAttack = state.value.canAttackDown,
            onGetNeighbor = state.value::getDownNeighbor
        )
    }

    fun moveLeft() = viewModelScope.launch {
        move(
            canMove = state.value.canMoveLeft,
            onGetNeighbor = state.value::getLeftNeighbor
        )
    }

    fun moveRight() = viewModelScope.launch {
        move(
            canMove = state.value.canMoveRight,
            onGetNeighbor = state.value::getRightNeighbor
        )
    }

    fun moveUp() = viewModelScope.launch {
        move(
            canMove = state.value.canMoveUp,
            onGetNeighbor = state.value::getUpNeighbor
        )
    }

    fun moveDown() = viewModelScope.launch {
        move(
            canMove = state.value.canMoveDown,
            onGetNeighbor = state.value::getDownNeighbor
        )
    }


    private fun move(
        canMove: Boolean,
        onGetNeighbor: (Int) -> Int?
    ) {
        if (!canMove) return
        val gameField = state.value.gameField.toMutableList()
        val heroLocationIndex = state.value.heroLocationIndex
        var hero = (gameField[heroLocationIndex] as? Cell.HeroOccupied)?.hero ?: return
        val index = onGetNeighbor(heroLocationIndex) ?: return
        val cell = gameField[index]
        when (cell) {
            is Cell.Treasure -> {
                hero = hero.copy(hasTreasure = true)
            }

            is Cell.Potion -> {
                hero = hero.copy(potionAmount = hero.potionAmount + 1)
            }

            is Cell.Exit -> {
                if (hero.hasTreasure) {
                    println("Win ")// TODO: win
                    return
                } else {
                    println("You don't have treasure dialog") // TODO: show dialog
                    return
                }
            }

            else -> {}
        }
        gameField[heroLocationIndex] = Cell.Empty
        gameField[index] = Cell.HeroOccupied(hero)
        gameField.doMummiesActions()
        _state.update { it.copy(gameField = gameField) }
    }

    private fun attack(
        canAttack: Boolean,
        onGetNeighbor: (Int) -> Int?
    ) {
        if (!canAttack) return
        val gameField = state.value.gameField.toMutableList()
        val heroLocationIndex = state.value.heroLocationIndex
        val hero = (gameField[heroLocationIndex] as? Cell.HeroOccupied)?.hero ?: return
        val index = onGetNeighbor(heroLocationIndex) ?: return
        val cell = gameField[index]
        var mummy = (cell as? Cell.MummyOccupied)?.mummy ?: return
        val heroAttack = (hero.minAttack..hero.maxAttack).random()
        mummy = mummy.copy(health = maxOf(mummy.health - heroAttack, 0))
        println("Hero attack: $heroAttack. Mummy health: ${mummy.health}")
        if (mummy.health > 0) {
            gameField[index] = Cell.MummyOccupied(mummy)
        } else {
            gameField[index] = Cell.Empty
        }
        gameField.doMummiesActions()
        _state.update { it.copy(gameField = gameField) }
    }

    private fun MutableList<Cell>.doMummiesActions() {
        // TODO mummies moves and attacks
        val mummyIndices = mapIndexedNotNull { index, cell ->
            if (cell is Cell.MummyOccupied) index else null
        }
        val heroIndex = indexOfFirst { it is Cell.HeroOccupied }
        mummyIndices.forEach { mummyIndex ->
            val mummy = (this[mummyIndex] as? Cell.MummyOccupied)?.mummy ?: return
            if (state.value.areNeighbors(heroIndex, mummyIndex)) {
                val mummyAttack = (mummy.minAttack..mummy.maxAttack).random()
                var hero = (this[heroIndex] as? Cell.HeroOccupied)?.hero ?: return
                hero = hero.copy(health = maxOf(hero.health - mummyAttack, 0))
                println("Mummy attack: $mummyAttack. Hero health: ${hero.health}")
                if (hero.health > 0) {
                    this[heroIndex] = Cell.HeroOccupied(hero)
                } else {
                    // TODO: lose
                    println("You lose")
                }
            } else {
                val potentialMoves = listOfNotNull(
                    state.value.getUpNeighbor(mummyIndex),
                    state.value.getDownNeighbor(mummyIndex),
                    state.value.getLeftNeighbor(mummyIndex),
                    state.value.getRightNeighbor(mummyIndex),
                ).filter { this[it] is Cell.Empty }
                var moved = false
                potentialMoves.forEach { potentialMove ->
                    if (!moved && state.value.areNeighbors(potentialMove, heroIndex)) {
                        this[potentialMove] = Cell.MummyOccupied(mummy)
                        this[mummyIndex] = Cell.Empty
                        moved = true
                    }
                }
            }
        }
    }
}
