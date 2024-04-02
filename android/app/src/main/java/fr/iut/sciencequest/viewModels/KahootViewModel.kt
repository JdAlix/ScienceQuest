package fr.iut.sciencequest.viewModels

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.iut.sciencequest.model.repositories.question.IQuestionRepository
import fr.iut.sciencequest.model.repositories.question.QuestionAPIRepository
import fr.iut.sciencequest.viewModels.uixStates.KahootUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class KahootViewModel(
    val questionRepo: IQuestionRepository
): ViewModel() {
    var uiState = MutableStateFlow(KahootUIState())

    private val handler = Handler(Looper.getMainLooper())

    fun lancerPartie() {
        viewModelScope.launch {
            questionRepo.fetchQuestions(2)
            Log.d("KahootViewModel","J'ai trouvé ${questionRepo.questions.value.size} questions")
            var count = 1
            for (question in questionRepo.questions.value) {
                handler.postDelayed(
                    {
                        Log.d("KahootViewModel", "J'actualise les questions")
                        uiState.value = KahootUIState(
                            question,
                            duréePartie = uiState.value.duréePartie,
                            nbPoints = uiState.value.nbPoints,
                            reponseChoisie = false
                        )
                    },
                    uiState.value.duréePartie * count
                )
                count++
            }
        }
    }

    // NOTE : tpsReponse en ms
    fun ajouterPoints(tpsReponse: Long) {
        Log.d("KahootViewModel","Je reçois une réponse")
        if (uiState.value.reponseChoisie) {
            Log.d("KahootViewModel","Le joueur a déjà répondu")
            return
        }
        val nbPoints: Int = (10_000 - tpsReponse).toInt()
        uiState.value = KahootUIState(uiState.value.question,
            duréePartie = uiState.value.duréePartie,
            nbPoints = uiState.value.nbPoints + nbPoints,
            reponseChoisie = true)
        Log.d("KahootViewModel","Le joueur à ${uiState.value.nbPoints}")
    }
    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>
            ): T {
                return KahootViewModel(
                    QuestionAPIRepository()
                ) as T
            }
        }
    }
}