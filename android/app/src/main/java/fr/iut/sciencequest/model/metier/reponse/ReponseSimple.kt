package fr.iut.sciencequest.model.metier.reponse

class ReponseSimple {
    val id: Int
    val reponse: String

    constructor(id: Int, reponse: String) {
        this.id = id
        this.reponse = reponse
    }
}