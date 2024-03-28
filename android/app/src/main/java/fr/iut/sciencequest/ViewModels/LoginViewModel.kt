package fr.iut.sciencequest.ViewModels

import androidx.lifecycle.ViewModel
import fr.iut.sciencequest.ViewModels.UiStates.LoginUIState
import kotlinx.coroutines.flow.MutableStateFlow

class LoginViewModel: ViewModel() {
    var uiState = MutableStateFlow(LoginUIState())

    fun setPseudo(pseudo: String) {
        uiState.value = LoginUIState(pseudo, uiState.value.mdp)
    }

    fun setMdp(mdp: String) {
        uiState.value = LoginUIState(uiState.value.pseudo, mdp)
    }
}