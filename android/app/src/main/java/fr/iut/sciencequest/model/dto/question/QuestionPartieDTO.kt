package fr.iut.sciencequest.model.dto.question

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class QuestionPartieDTO (
    @SerialName("questionActuel")
    val question: QuestionWithSimpleResponseDTO?,
    @SerialName("tempsLimiteReponse")
    val limitTime: String?
)