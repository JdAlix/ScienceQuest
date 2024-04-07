package fr.iut.sciencequest.model.dto.partie

import fr.iut.sciencequest.model.dto.joueur.JoueurScoreDTO
import kotlinx.serialization.Serializable

@Serializable
class LaunchedPartieDTO (
    val status: String,
    val scores: List<JoueurScoreDTO>
)