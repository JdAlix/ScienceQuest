package fr.iut.sciencequest.model.metier

class Reponse (
    val id: Int,
    val reponse: String,
    val question: Question,
    val scientifique: Scientifique
) {}

