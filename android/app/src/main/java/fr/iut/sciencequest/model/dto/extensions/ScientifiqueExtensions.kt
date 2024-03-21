package fr.iut.sciencequest.model.dto.extensions

import fr.iut.sciencequest.model.dto.ScientifiqueDTO
import fr.iut.sciencequest.model.metier.Scientifique

fun ScientifiqueDTO.ToModel(): Scientifique {
    val model = Scientifique(
        id = this.id,
        descriptif = this.descriptif,
        nom = this.nom,
        prenom = this.photo,
        photo = this.photo,
        difficulte = this.difficulte.ToModel(),
        sexe = this.sexe,
        ratioTrouve = this.ratioTrouve,
        thematique = this.thematique.ToModel()
    )
    return model
}

fun List<ScientifiqueDTO>.ToModel(): List<Scientifique> {
    val liste = ArrayList<Scientifique>();
    for (dto in this) {
        liste.add(dto.ToModel())
    }
    return liste
}