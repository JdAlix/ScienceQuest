package fr.iut.sciencequest.model.dto.extensions

import fr.iut.sciencequest.model.dto.JeuDTO
import fr.iut.sciencequest.model.metier.Jeu

fun JeuDTO.toModel(): Jeu {
    return Jeu(
        this.id,
        this.nom,
        this.nbrParties
    )
}