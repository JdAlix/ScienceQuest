package fr.iut.sciencequest.model.buisness

import fr.iut.sciencequest.ViewModels.UiStates.PenduUIState

// mot : mot à trouver
// motAct : état actuel du mot trouvé par l'utilisateur
public fun PlayAction(uiState: PenduUIState): PenduUIState {
    if (uiState.motATrou.contains(uiState.lettreJoue)) {
        return PenduUIState(false,uiState.nbViesRestantes,uiState.motATrouver,uiState.motATrou,' ')
    }
    if (uiState.motATrouver.contains(uiState.lettreJoue)) {
        var nvMotATrou = uiState.motATrou
        for (index in uiState.motATrouver.indices) {
            if (uiState.motATrouver[index] == uiState.lettreJoue) {
                nvMotATrou = nvMotATrou.replaceRange(index,index, uiState.lettreJoue.toString())
            }
        }
        return PenduUIState(true,
            uiState.nbViesRestantes,
            uiState.motATrouver,
            uiState.motATrou,   
            ' ')
    } else {
        return PenduUIState(true,
            uiState.nbViesRestantes - 1,
            uiState.motATrouver,
            uiState.motATrou,
            ' ')
    }
}

