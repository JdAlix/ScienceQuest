package fr.iut.sciencequest.model.metier.partie

import fr.iut.sciencequest.model.metier.Difficulte
import fr.iut.sciencequest.model.metier.Thematique
import fr.iut.sciencequest.model.metier.joueur.JoueurSimple

class PartieKahoot (
    val id: Int,
    val code: String,
    val thematiques: List<Thematique>,
    val joueurs: List<JoueurSimple>,
    val difficulte: Difficulte
)