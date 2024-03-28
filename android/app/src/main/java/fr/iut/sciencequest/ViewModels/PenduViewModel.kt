package fr.iut.sciencequest.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.iut.sciencequest.ViewModels.UiStates.PenduUIState
import fr.iut.sciencequest.model.buisness.Scientifique.fetchScientifiqueById
import fr.iut.sciencequest.model.buisness.Scientifique.fetchScientifiques
import fr.iut.sciencequest.model.dto.extensions.ToModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PenduViewModel : ViewModel() {
    var uiState = MutableStateFlow(PenduUIState())

    fun InitPartie() {
        Log.d("PenduViewModel","Un utilisateur initialise une partie")
        viewModelScope.launch {
            fetchScientifiqueById(1).collect {
                val nomComplet = it.prenom + " " + it.nom
                Log.d("ViewModelPendu",nomComplet)
                var motATrou = ""
                for (chr in nomComplet) {
                    motATrou += if (chr == ' ') {
                        ' '
                    } else {
                        '_'
                    }
                }
                uiState.value = PenduUIState(
                    isActionGood = true,
                    motATrouver = nomComplet,
                    motATrou = motATrou
                )
            }
        }
    }

    // mot : mot à trouver
    // motAct : état actuel du mot trouvé par l'utilisateur
    fun PlayAction(lettre: Char) {
        Log.d("PenduViewModel","Un utilisateur joue une action")
        if (lettre == ' ' || uiState.value.motATrou.contains(lettre)) {
            Log.d("PenduViewModel","L'utilisateur a fait une action invalide")
            uiState.value = PenduUIState(false,
                false,
                uiState.value.nbViesRestantes,
                uiState.value.motATrouver,
                uiState.value.motATrou
            )
        } else if (uiState.value.motATrouver.contains(lettre)) {
            Log.d("PenduViewModel","L'utilisateur a trouvé une lettre")
            var nvMotATrou = uiState.value.motATrou
            for (index in uiState.value.motATrouver.indices) {
                if (uiState.value.motATrouver[index] == lettre) {
                    nvMotATrou = nvMotATrou.replaceRange(index,index + 1, lettre.toString())
                }
            }
            var isWon = false
            if (nvMotATrou.equals(uiState.value.motATrouver)) {
                isWon = true
            }
            uiState.value = PenduUIState(isWon,
                true,
                uiState.value.nbViesRestantes,
                uiState.value.motATrouver,
                nvMotATrou
            )
        } else {
            Log.d("PenduViewModel","L'utilisateur s'est trompé de lettre")
            uiState.value = PenduUIState(false,
                true,
                uiState.value.nbViesRestantes - 1,
                uiState.value.motATrouver,
                uiState.value.motATrou
            )
        }
    }
}