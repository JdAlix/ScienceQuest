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

            _uiState.value = KahootUIState(
                    questionRepo.questions.value.get(0),
                    dureePartie = uiState.value.dureePartie,
                    nbPoints = uiState.value.nbPoints,
                    reponseChoisie = false
            )
            for (index: Int in 1..questionRepo.questions.value.size) {
                handler.postDelayed(
                    {
                        _uiState.value = KahootUIState(
                            questionRepo.questions.value.get(index),
                            dureePartie = uiState.value.dureePartie,
                            nbPoints = uiState.value.nbPoints,
                            reponseChoisie = false
                        )
                    },
                    uiState.value.dureePartie * index
                )
            }
        }
    }

    // NOTE : tpsReponse en ms
    fun ajouterPoints(tpsReponse: Long) {
        if (tpsReponse < 0) {
            throw IllegalArgumentException("ERREUR: Temps négatif donné à l'ajout de points")
        } else if (tpsReponse > uiState.value.dureePartie) {
            throw IllegalArgumentException("ERREUR: Utilsateur à répondu trop lentement")
        }
        if (uiState.value.reponseChoisie) {
            return
        }
        val nbPoints: Int = (uiState.value.dureePartie - tpsReponse).toInt()
        _uiState.value = KahootUIState(uiState.value.question,
            dureePartie = uiState.value.dureePartie,
            nbPoints = uiState.value.nbPoints + nbPoints,
            reponseChoisie = true)
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