package fr.iut.sciencequest.viewModels

import androidx.lifecycle.ViewModel
import fr.iut.sciencequest.viewModels.uiStates.KahootJoinUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class KahootJoinViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(KahootJoinUIState())
    val uiState = _uiState.asStateFlow()

    fun setCode(code: String) {
        if (code.length >= 6) {
            return
        }
        _uiState.value = KahootJoinUIState(code)
    }
}