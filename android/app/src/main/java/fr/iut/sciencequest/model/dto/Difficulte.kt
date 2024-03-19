package fr.iut.sciencequest.model.dto

import kotlinx.serialization.Serializable;

@Serializable
data class DifficulteDTO (
    val id: Int,
    val libelle: String
)