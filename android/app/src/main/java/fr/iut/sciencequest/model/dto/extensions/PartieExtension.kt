package fr.iut.sciencequest.model.dto.extensions

import fr.iut.sciencequest.model.dto.partie.PartieDTO
import fr.iut.sciencequest.model.metier.partie.Partie

fun PartieDTO.toModel(): Partie {
    return Partie(
        this.id,
        this.codeInvitation,
        this.joueurs.toModel(),
        this.jeu.toModel(),
        this.thematiques.toModel()
    )
}