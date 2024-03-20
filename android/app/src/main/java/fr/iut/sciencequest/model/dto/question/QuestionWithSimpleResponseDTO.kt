package fr.iut.sciencequest.model.dto.question

import fr.iut.sciencequest.model.dto.reponse.ReponseDTO
import fr.iut.sciencequest.model.dto.reponse.ReponseSimpleDTO
import kotlinx.serialization.Serializable

@Serializable
class QuestionWithSimpleResponseDTO(
    val id: Int,
    val question: String,
    val reponses: List<ReponseSimpleDTO>
)