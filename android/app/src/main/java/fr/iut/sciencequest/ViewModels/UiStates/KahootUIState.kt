package fr.iut.sciencequest.ViewModels.UiStates

import fr.iut.sciencequest.model.dto.question.QuestionWithSimpleResponseDTO
import fr.iut.sciencequest.model.dto.reponse.ReponseSimpleDTO
import fr.iut.sciencequest.stub.StubQuestionWithReponses

data class KahootUIState (
    val question: QuestionWithSimpleResponseDTO = StubQuestionWithReponses
)