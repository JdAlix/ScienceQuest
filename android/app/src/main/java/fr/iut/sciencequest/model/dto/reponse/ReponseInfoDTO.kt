package fr.iut.sciencequest.model.dto.reponse

import kotlinx.serialization.Serializable

@Serializable
data class ReponseInfoDTO (
    val idJoueur: Int,
    val idReponse: Int
)