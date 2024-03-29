package fr.iut.sciencequest.ViewModels

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.ViewModel
import fr.iut.sciencequest.ViewModels.UiStates.KahootUIState
import fr.iut.sciencequest.stub.StubQuestionWithReponses2
import kotlinx.coroutines.flow.MutableStateFlow

class KahootViewModel: ViewModel() {
    var uiState = MutableStateFlow(KahootUIState())

    private val handler = Handler(Looper.getMainLooper())

    fun lancerPartie() {
        handler.postDelayed(
            {
                Log.d("KahootViewModel","J'actualise les questions")
                uiState.value = KahootUIState(StubQuestionWithReponses2,
                    duréePartie = uiState.value.duréePartie,
                    nbPoints = uiState.value.nbPoints,
                    reponseChoisie = false)
            },
            uiState.value.duréePartie * 1000
        )
    }

    // NOTE : tpsReponse en ms
    fun ajouterPoints(tpsReponse: Int) {

        val nbPoints = if(uiState.value.reponseChoisie) {
            10_000 - tpsReponse
        } else {
            0
        }
        uiState.value = KahootUIState(uiState.value.question,
            duréePartie = uiState.value.duréePartie,
            nbPoints = uiState.value.nbPoints + nbPoints,
            reponseChoisie = false)
        Log.d("KahootViewModel","Le joueur à ${uiState.value.reponseChoisie}")
    }
}