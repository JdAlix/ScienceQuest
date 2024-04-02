package fr.iut.sciencequest.model.metier.question

import fr.iut.sciencequest.model.metier.reponse.Reponse

class Question(
    val id: Int,
    val question: String,
    val reponses: List<Reponse>
) {}

