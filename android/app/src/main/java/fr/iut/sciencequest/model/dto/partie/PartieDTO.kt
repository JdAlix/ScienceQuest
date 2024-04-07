package fr.iut.sciencequest.model.dto.partie

import fr.iut.sciencequest.model.dto.JeuDTO
import fr.iut.sciencequest.model.dto.ThematiqueDTO
import fr.iut.sciencequest.model.dto.joueur.JoueurSimpleDTO

class PartieDTO (
    val id: Int,
    val codeInvitation: String,
    val joueurs: List<JoueurSimpleDTO>,
    val jeu: JeuDTO,
    val thematiques: List<ThematiqueDTO>
)