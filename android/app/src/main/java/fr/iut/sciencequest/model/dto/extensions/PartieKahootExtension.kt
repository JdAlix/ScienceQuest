package fr.iut.sciencequest.model.dto.extensions

import fr.iut.sciencequest.model.dto.partie.PartieKahootDTO
import fr.iut.sciencequest.model.metier.partie.PartieKahoot

fun PartieKahootDTO.toModel(): PartieKahoot {
    return PartieKahoot(
        this.id,
        this.code,
        this.thematiques.toModel(),
        this.joueurs.toModel(),
        this.difficulte.ToModel()
    )
}