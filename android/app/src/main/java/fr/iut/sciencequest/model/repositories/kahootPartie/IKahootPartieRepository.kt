package fr.iut.sciencequest.model.repositories.kahootPartie

import fr.iut.sciencequest.model.dto.partie.NouvellePartieDTO
import fr.iut.sciencequest.model.metier.partie.PartieKahoot
import fr.iut.sciencequest.model.metier.question.QuestionPartie
import kotlinx.coroutines.flow.StateFlow

interface IKahootPartieRepository {
    val partie: StateFlow<PartieKahoot>
    val question: StateFlow<QuestionPartie>
    val isStarted: StateFlow<Boolean>
    val isReponseValid: StateFlow<Boolean>

    suspend fun createPartie(nvPartie: NouvellePartieDTO)
    suspend fun startPartie(code: String)
    suspend fun getQuestion(code: String)
    suspend fun postReponse(code: String, idJoueur: Int, idReponse: Int)
}