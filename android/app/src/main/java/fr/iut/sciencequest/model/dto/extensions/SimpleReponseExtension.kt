package fr.iut.sciencequest.model.dto.extensions

import fr.iut.sciencequest.model.dto.reponse.ReponseSimpleDTO
import fr.iut.sciencequest.model.metier.reponse.ReponseSimple

fun ReponseSimpleDTO.ToModel(): ReponseSimple {
    return ReponseSimple(
        this.id,
        this.reponse
    )
}

fun List<ReponseSimpleDTO>.ToModel(): List<ReponseSimple> {
    val liste = ArrayList<ReponseSimple>()
    for (reponse in this) {
        liste.add(reponse.ToModel())
    }
    return liste
}