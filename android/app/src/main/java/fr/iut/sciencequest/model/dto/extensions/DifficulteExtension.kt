package fr.iut.sciencequest.model.dto.extensions

import fr.iut.sciencequest.model.dto.DifficulteDTO
import fr.iut.sciencequest.model.metier.Difficulte

fun DifficulteDTO.ToModel(): Difficulte {
    val model = Difficulte(
        id = this.id,
        libelle = this.libelle
    )
    return model
}