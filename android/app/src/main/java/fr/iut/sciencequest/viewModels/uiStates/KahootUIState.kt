package fr.iut.sciencequest.viewModels.uiStates

import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.metier.question.QuestionWithSimpleReponse
import fr.iut.sciencequest.stub.StubQuestionWithReponses

data class KahootUIState (
    val question: QuestionWithSimpleReponse = StubQuestionWithReponses.ToModel(),
    val reponseChoisie: Boolean = false,
    // NOTE : Supposé en millisecondes
    val duréePartie: Long = 10_000,
    val nbPoints: Int = 0
)