package fr.iut.sciencequest.viewModels.uixStates

import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.metier.Difficulte
import fr.iut.sciencequest.model.metier.partie.Partie
import fr.iut.sciencequest.model.metier.partie.PartieKahoot
import fr.iut.sciencequest.model.metier.question.QuestionPartie
import fr.iut.sciencequest.model.metier.question.QuestionWithSimpleReponse
import fr.iut.sciencequest.stub.StubQuestionWithReponses
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

data class KahootUIState (
    val partie: PartieKahoot = PartieKahoot(0,"", emptyList(), emptyList(), Difficulte(0,"")),
    val questionPartie: QuestionPartie = QuestionPartie(QuestionWithSimpleReponse(0,"", emptyList()), LocalDateTime(1,1,1,1,1).toInstant(
        TimeZone.UTC)),
    val reponseChoisie: Boolean = false,
    // NOTE : Supposé en millisecondes
    val dureePartie: Long = 10_000,
    val nbPoints: Int = 0
)