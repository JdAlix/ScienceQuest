package fr.iut.sciencequest.model.dto

import fr.iut.sciencequest.model.dto.joueur.JoueurSimpleDTO

class PartieDTO (
    val id: Int,
    val codeInvitation: String,
    val joueurs: List<JoueurSimpleDTO>,
    val jeu: JeuDTO,
    val thematiques: List<ThematiqueDTO>
)