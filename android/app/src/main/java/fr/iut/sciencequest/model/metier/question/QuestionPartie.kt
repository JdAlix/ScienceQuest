package fr.iut.sciencequest.model.metier.question

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime

class QuestionPartie (
    val question: QuestionWithSimpleReponse?,
    val date: Instant
)