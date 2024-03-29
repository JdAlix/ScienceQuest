package fr.iut.sciencequest.stub

import fr.iut.sciencequest.model.dto.question.QuestionWithSimpleResponseDTO
import fr.iut.sciencequest.model.dto.reponse.ReponseSimpleDTO

object StubQuestionWithReponses: QuestionWithSimpleResponseDTO(
    id = 0,
    question = "Ceci est une question ?",
    reponses = listOf<ReponseSimpleDTO>(
        ReponseSimpleDTO(id=0, "Reponse 1"),
        ReponseSimpleDTO(id=1, "Reponse 2"),
        ReponseSimpleDTO(id=2, "Reponse 3"),
        ReponseSimpleDTO(id=3, "Reponse 4"),
    )
)
{}

object StubQuestionWithReponses2: QuestionWithSimpleResponseDTO(
    id = 1,
    question = "Ceci est une autre question ?",
    reponses = listOf(
        ReponseSimpleDTO(id = 4, "Moi une reponse?"),
        ReponseSimpleDTO(id = 5, "La réponse A"),
        ReponseSimpleDTO(id = 6, "Je suis faux"),
        ReponseSimpleDTO(id = 5, "Toutes les réponses")
    )
)