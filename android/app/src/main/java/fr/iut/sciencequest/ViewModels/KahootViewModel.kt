package fr.iut.sciencequest.ViewModels

import androidx.lifecycle.ViewModel
import fr.iut.sciencequest.ViewModels.UiStates.KahootUIState
import kotlinx.coroutines.flow.MutableStateFlow

class KahootViewModel: ViewModel() {
    var uiState = MutableStateFlow(KahootUIState())
}