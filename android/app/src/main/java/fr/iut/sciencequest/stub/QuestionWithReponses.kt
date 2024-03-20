package fr.iut.sciencequest.stub

import fr.iut.sciencequest.model.dto.question.QuestionWithSimpleResponseDTO
import fr.iut.sciencequest.model.dto.reponse.ReponseSimpleDTO

object StubQuestionWithReponses: QuestionWithSimpleResponseDTO(
    id = 0,
    question = "Ceci est une question ?",
    reponses = listOf<ReponseSimpleDTO>(
        ReponseSimpleDTO(id=0, "Reponse 1"),
        ReponseSimpleDTO(id=1, "Reponse 2"),
        ReponseSimpleDTO(id=1, "Reponse 3"),
        ReponseSimpleDTO(id=1, "Reponse 4"),
    )
)
{}