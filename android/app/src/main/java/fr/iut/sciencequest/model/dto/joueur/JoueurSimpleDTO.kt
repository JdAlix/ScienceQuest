package fr.iut.sciencequest.model.dto.joueur

import kotlinx.serialization.Serializable

@Serializable
class JoueurSimpleDTO (
    val id: Int,
    val pseudo: String,
)