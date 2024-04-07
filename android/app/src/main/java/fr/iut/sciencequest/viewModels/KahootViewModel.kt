package fr.iut.sciencequest.viewModels

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.iut.sciencequest.model.dto.partie.NouvellePartieDTO
import fr.iut.sciencequest.model.repositories.kahootPartie.IKahootPartieRepository
import fr.iut.sciencequest.model.repositories.kahootPartie.KahootAPIRepository
import fr.iut.sciencequest.viewModels.uixStates.KahootUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

class KahootViewModel(
    val kahootRepo: IKahootPartieRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(KahootUIState())
    val uiState = _uiState.asStateFlow()

    private val handler = Handler(Looper.getMainLooper())

    fun createPartie(idJoueur: Int, idDifficulte: Int, thematiques: List<Int>) {
        viewModelScope.launch {
            val infosToCreate = NouvellePartieDTO(idJoueur, thematiques, idDifficulte)
            kahootRepo.createPartie(infosToCreate)

            _uiState.value = KahootUIState(
                dureePartie = _uiState.value.dureePartie,
                nbPoints = _uiState.value.nbPoints,
                reponseChoisie = false,
                partie = kahootRepo.partie.value
            )
        }
    }

    fun lancerPartie() {
        viewModelScope.launch {
            kahootRepo.startPartie(_uiState.value.partie.code)

            _uiState.value = KahootUIState(
                partie = kahootRepo.partie.value,
                nbPoints = _uiState.value.nbPoints,
                reponseChoisie = false,
                dureePartie = uiState.value.dureePartie
            )
        }
    }

    fun updateQuestion(goToResult: () -> Unit) {
        viewModelScope.launch {
            Log.d("Hop j'attends","a")
            kahootRepo.getQuestion(_uiState.value.partie.code)
            if (kahootRepo.question.value.date == LocalDateTime(1,1,1,1,1,1,1).toInstant(TimeZone.UTC)) {
                Log.d("Je passe là","j'ai fini le kahoot")
                goToResult()
            } else {
                _uiState.value = KahootUIState(
                    questionPartie = kahootRepo.question.value,
                    nbPoints = _uiState.value.nbPoints,
                    reponseChoisie = false,
                    partie = _uiState.value.partie
                )
                val now: Instant = Clock.System.now()
                val today: LocalDateTime = now.toLocalDateTime(TimeZone.currentSystemDefault())

                handler.postDelayed(
                    {
                        Log.d("PostDelayed","je vais chercher la prochaine question")
                        updateQuestion(goToResult)
                    },
                    uiState.value.dureePartie
                )
            }
        }
    }

    // NOTE : tpsReponse en ms
    fun ajouterPoints(tpsReponse: Long, reponseId: Int) {
        var isResponseValid = false
        viewModelScope.launch {
            kahootRepo.postReponse(_uiState.value.partie.code,1,reponseId)
            isResponseValid = kahootRepo.isReponseValid.value
        }
        if (tpsReponse < 0) {
            throw IllegalArgumentException("ERREUR: Temps négatif donné à l'ajout de points")
        } else if (tpsReponse > uiState.value.dureePartie) {
            throw IllegalArgumentException("ERREUR: Utilsateur à répondu trop lentement")
        }
        if (uiState.value.reponseChoisie) {
            return
        }
        val nbPoints: Int = if (isResponseValid) {
            (uiState.value.dureePartie - tpsReponse).toInt()
        } else {
            0
        }
        _uiState.value = KahootUIState(
            questionPartie = uiState.value.questionPartie,
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
                        KahootAPIRepository()
                ) as T
            }
        }
    }
}