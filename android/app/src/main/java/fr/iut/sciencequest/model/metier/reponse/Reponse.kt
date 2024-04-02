package fr.iut.sciencequest.model.metier.reponse

import fr.iut.sciencequest.model.metier.question.Question
import fr.iut.sciencequest.model.metier.Scientifique

class Reponse (
    val id: Int,
    val reponse: String,
    val question: Question,
    val scientifique: Scientifique
) {}

