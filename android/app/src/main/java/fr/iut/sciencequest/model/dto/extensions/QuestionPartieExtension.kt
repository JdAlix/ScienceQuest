package fr.iut.sciencequest.model.dto.extensions

import fr.iut.sciencequest.model.dto.question.QuestionPartieDTO
import fr.iut.sciencequest.model.metier.question.QuestionPartie
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

fun QuestionPartieDTO.toModel(): QuestionPartie {
    var limite: Instant
    if (this.limitTime == null) {
        limite = LocalDateTime(1,1,1,1,1,1,1).toInstant(TimeZone.UTC)
    } else {
        limite = Instant.parse(this.limitTime)
    }
    return QuestionPartie(
        this.question?.ToModel(),
        limite
    )
}