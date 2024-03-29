package fr.iut.sciencequest.model.metier

class Scientifique {
    val id: Int
    val nom: String
    val prenom: String
    val photo: String
    val descriptif: String
    val ratioTrouve: Float
    val sexe: Char
    val difficulte: Difficulte
    val thematique: Thematique

    constructor(id: Int,
                nom: String,
                prenom: String,
                photo: String,
                descriptif: String,
                ratioTrouve: Float,
                sexe: Char,
                difficulte: Difficulte,
                thematique: Thematique){
        this.id = id
        this.nom = nom
        this.prenom = prenom
        this.photo = photo
        this.descriptif = descriptif
        this.ratioTrouve = ratioTrouve
        this.sexe = sexe
        this.difficulte = difficulte
        this.thematique = thematique
    }
}