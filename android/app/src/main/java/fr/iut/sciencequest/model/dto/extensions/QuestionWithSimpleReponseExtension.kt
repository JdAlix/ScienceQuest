package fr.iut.sciencequest.model.dto.extensions

import fr.iut.sciencequest.model.dto.question.QuestionWithSimpleResponseDTO
import fr.iut.sciencequest.model.metier.question.QuestionWithSimpleReponse

fun QuestionWithSimpleResponseDTO.ToModel(): QuestionWithSimpleReponse {
    return QuestionWithSimpleReponse(
        this.id,
        this.question,
        this.reponses.ToModel()
    )
}

fun List<QuestionWithSimpleResponseDTO>.ToModel(): List<QuestionWithSimpleReponse> {
    val liste = ArrayList<QuestionWithSimpleReponse>()
    for (question in this) {
        liste.add(question.ToModel())
    }
    return liste
}