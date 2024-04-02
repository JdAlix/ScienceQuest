package fr.iut.sciencequest.model.dto.extensions

import fr.iut.sciencequest.model.dto.question.QuestionDTO
import fr.iut.sciencequest.model.dto.question.QuestionWithSimpleResponseDTO
import fr.iut.sciencequest.model.metier.question.Question
import fr.iut.sciencequest.model.metier.question.QuestionWithSimpleReponse

fun QuestionDTO.ToModel(): Question {
    return Question(
        this.id,
        this.question,
        this.reponses.ToModel()
    )
}

fun List<QuestionDTO>.ToModel(): List<Question> {
    val liste = mutableListOf<Question>()
    for (question in this) {
        liste.add(question.ToModel())
    }
    return liste
}

fun Question.ToQuestionWithSimpleReponse(): QuestionWithSimpleReponse {
    return QuestionWithSimpleReponse(
        this.id,
        this.question,
        this.reponses.ToSimpleReponses()
    )
}