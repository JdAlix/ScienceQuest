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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class KahootViewModel(
    val questionRepo: IQuestionRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(KahootUIState())
    val uiState = _uiState.asStateFlow()

    private val handler = Handler(Looper.getMainLooper())

    fun lancerPartie() {
        viewModelScope.launch {
            questionRepo.fetchQuestions(2)
            Log.d("KahootViewModel","J'ai trouvé ${questionRepo.questions.value.size} questions")

            _uiState.value = KahootUIState(
                    questionRepo.questions.value.get(0),
                    duréePartie = uiState.value.duréePartie,
                    nbPoints = uiState.value.nbPoints,
                    reponseChoisie = false
            )
            for (index: Int in 1..questionRepo.questions.value.size) {
                handler.postDelayed(
                    {
                        Log.d("KahootViewModel", "J'actualise les questions")
                        _uiState.value = KahootUIState(
                            questionRepo.questions.value.get(index),
                            duréePartie = uiState.value.duréePartie,
                            nbPoints = uiState.value.nbPoints,
                            reponseChoisie = false
                        )
                    },
                    uiState.value.duréePartie * index
                )
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
        _uiState.value = KahootUIState(uiState.value.question,
            duréePartie = uiState.value.duréePartie,
            nbPoints = uiState.value.nbPoints + nbPoints,
            reponseChoisie = true)
        Log.d("KahootViewModel","Le joueur à ${uiState.value.nbPoints}")
    }
    companion object {

        val ApiFactory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
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