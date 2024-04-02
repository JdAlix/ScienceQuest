package fr.iut.sciencequest.model.dto.extensions

import fr.iut.sciencequest.model.dto.reponse.ReponseDTO
import fr.iut.sciencequest.model.metier.reponse.Reponse
import fr.iut.sciencequest.model.metier.reponse.ReponseSimple

fun ReponseDTO.ToModel(): Reponse {
    return Reponse(
        this.id,
        this.reponse,
        this.question.ToModel(),
        this.scientifique.ToModel()
    )
}

fun List<ReponseDTO>.ToModel(): List<Reponse> {
    val liste = ArrayList<Reponse>()
    for (reponse in this) {
        liste.add(reponse.ToModel())
    }
    return liste
}

fun Reponse.ToSimpleReponse(): ReponseSimple {
    return ReponseSimple(
        this.id,
        this.reponse
    )
}

fun List<Reponse>.ToSimpleReponses(): List<ReponseSimple> {
    val liste = ArrayList<ReponseSimple>()
    for (reponse in this) {
        liste.add(reponse.ToSimpleReponse())
    }
    return liste
}