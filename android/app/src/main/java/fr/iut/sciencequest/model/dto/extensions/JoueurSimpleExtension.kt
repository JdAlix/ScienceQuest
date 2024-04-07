package fr.iut.sciencequest.model.dto.extensions

import fr.iut.sciencequest.model.dto.joueur.JoueurSimpleDTO
import fr.iut.sciencequest.model.metier.joueur.JoueurSimple

fun JoueurSimpleDTO.toModel(): JoueurSimple {
    return JoueurSimple(
        this.id,
        this.pseudo
    )
}

fun List<JoueurSimpleDTO>.toModel(): List<JoueurSimple> {
    val liste = mutableListOf<JoueurSimple>()
    for (joueur in this) {
        liste.add(joueur.toModel())
    }
    return liste
}