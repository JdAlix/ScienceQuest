package fr.iut.sciencequest.model.metier.reponse

class SimpleResponse {
    val id: Int
    val reponse: String

    constructor(id: Int, reponse: String) {
        this.id = id
        this.reponse = reponse
    }
}