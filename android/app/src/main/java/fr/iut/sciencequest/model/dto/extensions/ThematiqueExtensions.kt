package fr.iut.sciencequest.model.dto.extensions

import fr.iut.sciencequest.model.dto.ThematiqueDTO
import fr.iut.sciencequest.model.metier.Thematique

fun ThematiqueDTO.ToModel(): Thematique {
    val model = Thematique(
        id = this.id,
        libelle = this.libelle
    )
    return model
}