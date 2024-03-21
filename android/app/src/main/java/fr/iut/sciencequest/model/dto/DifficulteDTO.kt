package fr.iut.sciencequest.model.dto

import kotlinx.serialization.Serializable

@Serializable
class DifficulteDTO (
    val id: Int,
    val libelle: String
)