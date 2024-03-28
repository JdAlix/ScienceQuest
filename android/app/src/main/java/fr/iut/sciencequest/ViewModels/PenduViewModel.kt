package fr.iut.sciencequest.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import fr.iut.sciencequest.ViewModels.UiStates.PenduUIState
import kotlinx.coroutines.flow.MutableStateFlow

class PenduViewModel : ViewModel() {
    var uiState = MutableStateFlow<PenduUIState>(PenduUIState())

    // mot : mot à trouver
    // motAct : état actuel du mot trouvé par l'utilisateur
    public fun PlayAction(lettre: Char) {
        Log.d("PenduViewModel","Un utilisateur joue une action")
        if (uiState.value.motATrou.contains(lettre)) {
            Log.d("PenduViewModel","L'utilisateur a fait une action invalide")
            uiState.value = PenduUIState(false,
                uiState.value.nbViesRestantes,
                uiState.value.motATrouver,
                uiState.value.motATrou
            )
        }
        if (uiState.value.motATrouver.contains(lettre)) {
            Log.d("PenduViewModel","L'utilisateur a trouvé une lettre")
            var nvMotATrou = uiState.value.motATrou
            for (index in uiState.value.motATrouver.indices) {
                if (uiState.value.motATrouver[index] == lettre) {
                    nvMotATrou = nvMotATrou.replaceRange(index,index + 1, lettre.toString())
                }
            }
            uiState.value = PenduUIState(true,
                uiState.value.nbViesRestantes,
                uiState.value.motATrouver,
                nvMotATrou
            )
        } else {
            Log.d("PenduViewModel","L'utilisateur s'est trompé de lettre")
            uiState.value = PenduUIState(true,
                uiState.value.nbViesRestantes - 1,
                uiState.value.motATrouver,
                uiState.value.motATrou
            )
        }
    }
}