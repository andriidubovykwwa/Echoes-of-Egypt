package com.zambakcahayrican01.echoesofegypt.screen.achievements

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zambakcahayrican01.echoesofegypt.data.repository.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AchievementsViewModel(
    private val repository: GameRepository
) : ViewModel() {
    private val _state = MutableStateFlow(AchievementsState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    mummyKills = repository.getMummyKills(),
                    potionDrinks = repository.getPotionDrinks(),
                    damageTaken = repository.getDamageTaken(),
                    damageDealt = repository.getDamageDealt(),
                    potionPickups = repository.getPotionPickups(),
                    treasurePickups = repository.getTreasurePickups(),
                    completedLvls = repository.getCompletedLvls(),
                    completedLvlsWithoutKills = repository.getCompletedLvlsWithoutKills(),
                    maxCompletedLvl = repository.getMaxCompletedLvl()
                )
            }
        }
    }
}