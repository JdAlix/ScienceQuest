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

fun List<ThematiqueDTO>.toModel(): List<Thematique> {
    val liste = mutableListOf<Thematique>()
    for (thematique in this) {
        liste.add(thematique.ToModel())
    }
    return liste
}