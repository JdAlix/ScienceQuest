package fr.iut.sciencequest.model.dto

import kotlinx.serialization.Serializable

@Serializable
class QuestionDTO (
    val id: Int,
    val question: String,
    val reponses: List<ReponseDTO>
)