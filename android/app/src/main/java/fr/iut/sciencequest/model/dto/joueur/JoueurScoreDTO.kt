package fr.iut.sciencequest.model.dto.joueur

import fr.iut.sciencequest.model.metier.joueur.JoueurSimple
import kotlinx.serialization.Serializable

@Serializable
class JoueurScoreDTO (
    val joueur: JoueurSimpleDTO,
    val score: Int
)