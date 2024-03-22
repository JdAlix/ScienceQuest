package fr.iut.sciencequest.model.dto.reponse

import fr.iut.sciencequest.model.dto.question.QuestionDTO
import fr.iut.sciencequest.model.dto.ScientifiqueDTOs.ScientifiqueDTO
import kotlinx.serialization.Serializable

@Serializable
class ReponseDTO (
    val id: Int,
    val reponse: String,
    val question: QuestionDTO,
    val scientifique: ScientifiqueDTO
)