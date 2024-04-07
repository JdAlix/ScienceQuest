package fr.iut.sciencequest.model.dto.joueur

import fr.iut.sciencequest.model.dto.partie.PartieDTO

class JoueurDTO (
    val id: Int,
    val pseudo: String,
    val partie: PartieDTO
)