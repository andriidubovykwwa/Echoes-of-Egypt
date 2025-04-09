package com.zambakcahayrican01.echoesofegypt.screen.content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zambakcahayrican01.echoesofegypt.data.repository.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContentViewModel(
    private val repository: GameRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ContentState())
    val state = _state.asStateFlow()


    fun save(value: String) = viewModelScope.launch {
        repository.setSavedData(value)
        _state.update { it.copy(isSaved = true) }
    }

    fun addPopup() = viewModelScope.launch {
        _state.update { it.copy(hasPopup = true) }
    }

    fun closeAllPopups() = viewModelScope.launch {
        _state.update { it.copy(hasPopup = false) }
    }
}