package fr.iut.sciencequest.viewModels

import androidx.lifecycle.ViewModel
import fr.iut.sciencequest.viewModels.uiStates.LoginUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState = _uiState.asStateFlow()

    fun setPseudo(pseudo: String) {
        _uiState.value = LoginUIState(pseudo, uiState.value.mdp)
    }

    fun setMdp(mdp: String) {
        _uiState.value = LoginUIState(uiState.value.pseudo, mdp)
    }
}