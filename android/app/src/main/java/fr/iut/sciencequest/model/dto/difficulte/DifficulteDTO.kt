package fr.iut.sciencequest.model.dto.difficulte

import kotlinx.serialization.Serializable

@Serializable
class DifficulteDTO (
    val id: Int,
    val libelle: String
)