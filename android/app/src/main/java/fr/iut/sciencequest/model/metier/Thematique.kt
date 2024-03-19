package fr.iut.sciencequest.model.metier

class Thematique {
    val id: Int
    val libelle: String
    constructor(id: Int, libelle: String) {
        this.id = id
        this.libelle = libelle
    }
}