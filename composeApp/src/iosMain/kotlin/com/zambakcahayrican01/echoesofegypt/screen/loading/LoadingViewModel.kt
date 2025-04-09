package com.zambakcahayrican01.echoesofegypt.screen.loading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zambakcahayrican01.echoesofegypt.data.repository.GameRepository
import com.zambakcahayrican01.echoesofegypt.utils.NotificationManager
import com.zambakcahayrican01.echoesofegypt.utils.RequestManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoadingViewModel(
    private val repository: GameRepository
) : ViewModel() {
    private val _state = MutableStateFlow(LoadingState())
    val state = _state.asStateFlow()

    fun load() = viewModelScope.launch {
        val savedData = repository.getSavedData()
        if (!savedData.isNullOrBlank()) {
            _state.update { it.copy(content = savedData) }
            return@launch
        }
        val res = RequestManager.getRequestResult()
        if (res == null) {
            _state.update { it.copy(content = "") }
            return@launch
        }
        NotificationManager().init(res.key, res.tag) {
            _state.update { it.copy(content = res.data) }
        }
    }

}