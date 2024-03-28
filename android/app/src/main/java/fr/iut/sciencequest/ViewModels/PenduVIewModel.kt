package fr.iut.sciencequest.ViewModels

import fr.iut.sciencequest.ViewModels.UiStates.PenduUIState
import kotlinx.coroutines.flow.MutableStateFlow

class PenduVIewModel {
    var uiState = MutableStateFlow<PenduUIState>(PenduUIState())

    // mot : mot à trouver
    // motAct : état actuel du mot trouvé par l'utilisateur
    public fun PlayAction(lettre: Char) {
        if (uiState.value.motATrou.contains(lettre)) {
            uiState.value = PenduUIState(false,
                uiState.value.nbViesRestantes,
                uiState.value.motATrouver,
                uiState.value.motATrou
            )
        }
        if (uiState.value.motATrouver.contains(lettre)) {
            var nvMotATrou = uiState.value.motATrou
            for (index in uiState.value.motATrouver.indices) {
                if (uiState.value.motATrouver[index] == lettre) {
                    nvMotATrou = nvMotATrou.replaceRange(index,index, lettre.toString())
                }
            }
            uiState.value = PenduUIState(true,
                uiState.value.nbViesRestantes,
                uiState.value.motATrouver,
                uiState.value.motATrou
            )
        } else {
            uiState.value = PenduUIState(true,
                uiState.value.nbViesRestantes - 1,
                uiState.value.motATrouver,
                uiState.value.motATrou
            )
        }
    }
}