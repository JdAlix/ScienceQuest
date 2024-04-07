package fr.iut.sciencequest.model.metier.partie

import fr.iut.sciencequest.model.metier.Jeu
import fr.iut.sciencequest.model.metier.Thematique
import fr.iut.sciencequest.model.metier.joueur.JoueurSimple

class Partie (
    val id: Int,
    val codeInvitation: String,
    val joueurs: List<JoueurSimple>,
    val jeu: Jeu,
    val thematiques: List<Thematique>
)