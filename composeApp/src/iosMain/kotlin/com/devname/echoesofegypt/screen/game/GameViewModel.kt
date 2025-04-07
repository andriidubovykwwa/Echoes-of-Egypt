package com.devname.echoesofegypt.screen.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devname.echoesofegypt.data.game_params.Cell
import com.devname.echoesofegypt.data.game_params.Controls
import com.devname.echoesofegypt.data.game_params.GameParams
import com.devname.echoesofegypt.data.game_params.LevelGenerator
import com.devname.echoesofegypt.data.repository.GameRepository
import com.devname.echoesofegypt.utils.SoundManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel(
    private val repository: GameRepository
) : ViewModel() {
    private val _state = MutableStateFlow(GameState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(sounds = repository.getSounds()) }
        }
    }

    val controls = Controls(
        onMoveUp = ::moveUp,
        onMoveDown = ::moveDown,
        onMoveLeft = ::moveLeft,
        onMoveRight = ::moveRight,
        onAttackRight = ::attackRight,
        onAttackDown = ::attackDown,
        onAttackLeft = ::attackLeft,
        onAttackUp = ::attackUp,
        onDrinkPotion = ::drinkPotion,
    )

    fun restart() = viewModelScope.launch {
        _state.update { GameState() }
    }

    fun nextLevel() = viewModelScope.launch {
        val newLevel = state.value.level + 1
        _state.update {
            GameState(
                level = newLevel,
                gameField = LevelGenerator.generateLevel(newLevel),
                activeDialog = null,
                sounds = it.sounds
            )
        }
    }

    fun closeDialog() = viewModelScope.launch {
        _state.update { it.copy(activeDialog = null) }
    }

    private fun attackLeft() = viewModelScope.launch {
        attack(
            canAttack = state.value.canAttackLeft,
            onGetNeighbor = state.value::getLeftNeighbor
        )
    }

    private fun attackRight() = viewModelScope.launch {
        attack(
            canAttack = state.value.canAttackRight,
            onGetNeighbor = state.value::getRightNeighbor
        )
    }

    private fun attackUp() = viewModelScope.launch {
        attack(
            canAttack = state.value.canAttackUp,
            onGetNeighbor = state.value::getUpNeighbor
        )
    }

    private fun attackDown() = viewModelScope.launch {
        attack(
            canAttack = state.value.canAttackDown,
            onGetNeighbor = state.value::getDownNeighbor
        )
    }

    private fun moveLeft() = viewModelScope.launch {
        move(
            canMove = state.value.canMoveLeft,
            onGetNeighbor = state.value::getLeftNeighbor
        )
    }

    private fun moveRight() = viewModelScope.launch {
        move(
            canMove = state.value.canMoveRight,
            onGetNeighbor = state.value::getRightNeighbor
        )
    }

    private fun moveUp() = viewModelScope.launch {
        move(
            canMove = state.value.canMoveUp,
            onGetNeighbor = state.value::getUpNeighbor
        )
    }

    private fun moveDown() = viewModelScope.launch {
        move(
            canMove = state.value.canMoveDown,
            onGetNeighbor = state.value::getDownNeighbor
        )
    }

    private fun drinkPotion() = viewModelScope.launch {
        val gameField = state.value.gameField.toMutableList()
        val heroLocationIndex = state.value.heroLocationIndex
        var hero = (gameField[heroLocationIndex] as? Cell.HeroOccupied)?.hero ?: return@launch
        if (!hero.canDrinkPotion) return@launch
        SoundManager.playPotionDrink(state.value.sounds)
        hero = hero.copy(
            health = minOf(
                hero.health + GameParams.HEALING_POTION_RESTORE_VALUE,
                hero.maxHealth
            ),
            potionAmount = hero.potionAmount - 1
        )
        repository.registerPotionDrink()
        gameField[heroLocationIndex] = Cell.HeroOccupied(hero)
        gameField.doMummiesActions()
        _state.update { it.copy(gameField = gameField) }
    }


    private fun move(
        canMove: Boolean,
        onGetNeighbor: (Int) -> Int?
    ) {
        if (!canMove) return
        SoundManager.playStep(state.value.sounds)
        val gameField = state.value.gameField.toMutableList()
        val heroLocationIndex = state.value.heroLocationIndex
        var hero = (gameField[heroLocationIndex] as? Cell.HeroOccupied)?.hero ?: return
        val index = onGetNeighbor(heroLocationIndex) ?: return
        val cell = gameField[index]
        when (cell) {
            is Cell.Treasure -> {
                repository.registerTreasurePickup()
                SoundManager.playItemPickup(state.value.sounds)
                hero = hero.copy(hasTreasure = true)
            }

            is Cell.Potion -> {
                repository.registerPotionPickup()
                SoundManager.playItemPickup(state.value.sounds)
                hero = hero.copy(potionAmount = hero.potionAmount + 1)
            }

            is Cell.Exit -> {
                if (hero.hasTreasure) {
                    repository.registerCompletedLvl(state.value.level)
                    if (state.value.currentLvlKills == 0) {
                        repository.registerCompletedLvlWithoutKills()
                    }
                    SoundManager.playWin(state.value.sounds)
                    _state.update { it.copy(activeDialog = GameState.Dialog.LEVEL_COMPLETED) }
                    return
                } else {
                    _state.update { it.copy(activeDialog = GameState.Dialog.NO_TREASURE) }
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
        SoundManager.playAttack(state.value.sounds)
        val gameField = state.value.gameField.toMutableList()
        val heroLocationIndex = state.value.heroLocationIndex
        val hero = (gameField[heroLocationIndex] as? Cell.HeroOccupied)?.hero ?: return
        val index = onGetNeighbor(heroLocationIndex) ?: return
        val cell = gameField[index]
        var mummy = (cell as? Cell.MummyOccupied)?.mummy ?: return
        val heroAttack = (hero.minAttack..hero.maxAttack).random()
        mummy = mummy.copy(health = maxOf(mummy.health - heroAttack, 0))
        repository.registerDamageDealt(heroAttack)
        if (mummy.health > 0) {
            gameField[index] = Cell.MummyOccupied(mummy)
        } else {
            SoundManager.playMummyDeath(state.value.sounds)
            _state.update { it.copy(currentLvlKills = it.currentLvlKills + 1) }
            repository.registerMummyKill()
            gameField[index] = Cell.Empty
        }
        gameField.doMummiesActions()
        _state.update { it.copy(gameField = gameField) }
    }

    private fun MutableList<Cell>.doMummiesActions() {
        val mummyIndices = mapIndexedNotNull { index, cell ->
            if (cell is Cell.MummyOccupied) index else null
        }
        val heroIndex = indexOfFirst { it is Cell.HeroOccupied }
        if (heroIndex == -1) return // No hero found, skip

        mummyIndices.forEach { mummyIndex ->
            val mummy = (this[mummyIndex] as? Cell.MummyOccupied)?.mummy ?: return@forEach

            // Step 1: Check for possible moves towards empty cells
            val potentialMoves = listOfNotNull(
                state.value.getUpNeighbor(mummyIndex),
                state.value.getDownNeighbor(mummyIndex),
                state.value.getLeftNeighbor(mummyIndex),
                state.value.getRightNeighbor(mummyIndex),
            ).filter { this[it] is Cell.Empty }

            // Move mummy if a valid move is found
            potentialMoves.firstOrNull { state.value.areNeighbors(it, heroIndex) }?.let { move ->
                // Move mummy
                this[move] = Cell.MummyOccupied(mummy)
                this[mummyIndex] = Cell.Empty

                // After moving, check if the new position is adjacent to the hero and attack
                if (state.value.areNeighbors(move, heroIndex)) {
                    SoundManager.playAttack(state.value.sounds)
                    val mummyAttack = (mummy.minAttack..mummy.maxAttack).random()
                    var hero = (this[heroIndex] as? Cell.HeroOccupied)?.hero ?: return@forEach
                    hero = hero.copy(health = maxOf(hero.health - mummyAttack, 0))
                    repository.registerDamageTaken(mummyAttack)

                    if (hero.health > 0) {
                        this[heroIndex] = Cell.HeroOccupied(hero)
                    } else {
                        SoundManager.playLose(state.value.sounds)
                        _state.update { it.copy(activeDialog = GameState.Dialog.DEATH) }
                    }
                }
                return@forEach // No need to check further after moving and attacking
            }

            // If no move was made, directly check if the mummy is adjacent to the hero and attack
            if (state.value.areNeighbors(mummyIndex, heroIndex)) {
                SoundManager.playAttack(state.value.sounds)
                val mummyAttack = (mummy.minAttack..mummy.maxAttack).random()
                var hero = (this[heroIndex] as? Cell.HeroOccupied)?.hero ?: return@forEach
                hero = hero.copy(health = maxOf(hero.health - mummyAttack, 0))
                repository.registerDamageTaken(mummyAttack)

                if (hero.health > 0) {
                    this[heroIndex] = Cell.HeroOccupied(hero)
                } else {
                    SoundManager.playLose(state.value.sounds)
                    _state.update { it.copy(activeDialog = GameState.Dialog.DEATH) }
                }
            }
        }
    }

}
