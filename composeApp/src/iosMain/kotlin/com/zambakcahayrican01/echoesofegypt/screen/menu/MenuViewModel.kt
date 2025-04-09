package com.zambakcahayrican01.echoesofegypt.screen.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zambakcahayrican01.echoesofegypt.data.repository.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MenuViewModel(
    private val repository: GameRepository
) : ViewModel() {
    private val _state = MutableStateFlow(MenuState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(music = repository.getMusic(), sounds = repository.getSounds())
            }
        }
    }

    fun setMusic(value: Int) = viewModelScope.launch {
        val valueToSet = value.coerceIn(0, GameRepository.MAX_SOUND_VALUE)
        repository.setMusic(valueToSet)
        _state.update {
            it.copy(music = valueToSet)
        }
    }

    fun setSounds(value: Int) = viewModelScope.launch {
        val valueToSet = value.coerceIn(0, GameRepository.MAX_SOUND_VALUE)
        repository.setSounds(valueToSet)
        _state.update {
            it.copy(sounds = valueToSet)
        }
    }

    fun openSettings() = viewModelScope.launch {
        _state.update { it.copy(isSettingsOpened = true) }
    }

    fun closeSettings() = viewModelScope.launch {
        _state.update { it.copy(isSettingsOpened = false) }
    }
}