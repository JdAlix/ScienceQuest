package fr.iut.sciencequest.model.dto.reponse

import kotlinx.serialization.Serializable

@Serializable
class ReponseSimpleDTO(
    val id: Int,
    val reponse: String,
)