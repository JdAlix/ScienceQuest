package fr.iut.sciencequest.model.dto

import kotlinx.serialization.Serializable

@Serializable
class ReponseDTO (
    val id: Int,
    val reponse: String,
    val question: QuestionDTO,
    val scientifique: ScientifiqueDTO
)