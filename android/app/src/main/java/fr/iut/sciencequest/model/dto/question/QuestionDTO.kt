package fr.iut.sciencequest.model.dto.question

import fr.iut.sciencequest.model.dto.reponse.ReponseDTO
import kotlinx.serialization.Serializable

@Serializable
class QuestionDTO (
    val id: Int,
    val question: String,
    val reponses: List<ReponseDTO>
)