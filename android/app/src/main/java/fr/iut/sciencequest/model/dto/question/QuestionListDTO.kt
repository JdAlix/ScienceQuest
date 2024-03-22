package fr.iut.sciencequest.model.dto.question

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class QuestionListDTO {
    @SerialName("_embedded")
    val questions: List<QuestionWithSimpleResponseDTO>

    constructor(questions: List<QuestionWithSimpleResponseDTO>) {
        this.questions = questions
    }
}