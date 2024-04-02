package fr.iut.sciencequest.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.iut.sciencequest.ViewModels.UiStates.PenduUIState
import fr.iut.sciencequest.model.buisness.Scientifique.fetchScientifiqueById
import fr.iut.sciencequest.model.buisness.Scientifique.fetchScientifiquesInfos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PenduViewModel : ViewModel() {
    var uiState = MutableStateFlow(PenduUIState())

    fun InitPartie() {
        Log.d("PenduViewModel","Un utilisateur initialise une partie")
        viewModelScope.launch {
            fetchScientifiquesInfos().collect { scientifiquesInfos ->
                Log.e("PenduViewModel",scientifiquesInfos.nbScientfiques.toString())
                fetchScientifiqueById((1..scientifiquesInfos.nbScientfiques).random()).collect {
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
    }

    // mot : mot à trouver
    // motAct : état actuel du mot trouvé par l'utilisateur
    fun PlayAction(lettre: Char) {
        val lowerCaseLetter = lettre.lowercaseChar()
        Log.d("PenduViewModel","Un utilisateur joue une action")
        if (lettre == ' ' || uiState.value.lettresUtilises.contains(lowerCaseLetter)) {
            Log.d("PenduViewModel","L'utilisateur a fait une action invalide")
            uiState.value = PenduUIState(false,
                false,
                uiState.value.nbViesRestantes,
                uiState.value.motATrouver,
                uiState.value.motATrou,
                uiState.value.lettresUtilises
            )
            return
        } else if (uiState.value.motATrouver.lowercase().contains(lowerCaseLetter)) {
            Log.d("PenduViewModel","L'utilisateur a trouvé une lettre")
            var nvMotATrou = uiState.value.motATrou
            for (index in uiState.value.motATrouver.indices) {
                val letterToCheck = uiState.value.motATrouver[index]
                if (letterToCheck.lowercaseChar() == lowerCaseLetter) {
                    nvMotATrou = nvMotATrou.replaceRange(index,index + 1, letterToCheck.toString())
                }
            }

            val isWon = nvMotATrou == uiState.value.motATrouver

            uiState.value = PenduUIState(isWon,
                true,
                uiState.value.nbViesRestantes,
                uiState.value.motATrouver,
                nvMotATrou,
                uiState.value.lettresUtilises.plus(lowerCaseLetter)
            )
        } else {
            Log.d("PenduViewModel","L'utilisateur s'est trompé de lettre")
            uiState.value = PenduUIState(false,
                true,
                uiState.value.nbViesRestantes - 1,
                uiState.value.motATrouver,
                uiState.value.motATrou,
                uiState.value.lettresUtilises.plus(lowerCaseLetter)
            )
        }
    }
}