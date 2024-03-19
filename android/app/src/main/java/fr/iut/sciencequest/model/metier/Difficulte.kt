package fr.iut.sciencequest.model.metier

class Difficulte {
    val id: Int
    val libelle: String

    constructor(id: Int, libelle: String) {
        this.id = id
        this.libelle = libelle
    }
}