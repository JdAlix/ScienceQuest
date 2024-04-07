package fr.iut.sciencequest.model.dto.partie

import kotlinx.serialization.Serializable

@Serializable
data class NouvellePartieDTO (
    val idJoueur: Int,
    val thematiques: List<Int>,
    val idDifficulte: Int
)