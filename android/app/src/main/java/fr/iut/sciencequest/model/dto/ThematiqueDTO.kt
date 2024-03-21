package fr.iut.sciencequest.model.dto

import kotlinx.serialization.Serializable

@Serializable
class ThematiqueDTO (
    val id: Int,
    val libelle: String
)