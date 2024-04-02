package fr.iut.sciencequest.model.metier.question

import fr.iut.sciencequest.model.metier.reponse.Reponse
import fr.iut.sciencequest.model.metier.reponse.ReponseSimple

class QuestionWithSimpleReponse (
    val id: Int,
    val question: String,
    val reponses: List<ReponseSimple>
)