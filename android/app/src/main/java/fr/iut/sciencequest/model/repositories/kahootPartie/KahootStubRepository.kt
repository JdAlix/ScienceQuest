package fr.iut.sciencequest.model.repositories.kahootPartie

import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.dto.partie.NouvellePartieDTO
import fr.iut.sciencequest.model.metier.Difficulte
import fr.iut.sciencequest.model.metier.Thematique
import fr.iut.sciencequest.model.metier.joueur.JoueurSimple
import fr.iut.sciencequest.model.metier.partie.Partie
import fr.iut.sciencequest.model.metier.partie.PartieKahoot
import fr.iut.sciencequest.model.metier.question.QuestionPartie
import fr.iut.sciencequest.model.metier.question.QuestionWithSimpleReponse
import fr.iut.sciencequest.stub.StubQuestionWithReponses
import fr.iut.sciencequest.stub.StubQuestionWithReponses2
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

class KahootStubRepository : IKahootPartieRepository {

    private val _partie = MutableStateFlow<PartieKahoot>(PartieKahoot(0,"", emptyList(), emptyList(), Difficulte(0,"")))
    override val partie: StateFlow<PartieKahoot>
        get() = _partie.asStateFlow()

    private val _question = MutableStateFlow(QuestionPartie(StubQuestionWithReponses.ToModel(),
        LocalDateTime(1,1,1,1,1 ).toInstant(
        TimeZone.UTC)))
    override val question: StateFlow<QuestionPartie>
        get() = TODO("Not yet implemented")

    private val _isStarted = MutableStateFlow(false)
    override val isStarted: StateFlow<Boolean>
        get() = _isStarted.asStateFlow()

    private val _isResponseValid = MutableStateFlow(false)
    override val isReponseValid: StateFlow<Boolean>
        get() = _isResponseValid.asStateFlow()

    override suspend fun createPartie(nvPartie: NouvellePartieDTO) {
        _partie.value = PartieKahoot(
            0,
            "AAAAA",
            listOf(Thematique(nvPartie.thematiques.get(0),"")),
            listOf(JoueurSimple(nvPartie.idJoueur,"pseudo")),
            Difficulte(0,"")
        )
    }

    override suspend fun startPartie(code: String) {
        _isStarted.value = true
    }

    override suspend fun getQuestion(code: String) {
        _question.value = QuestionPartie(
            StubQuestionWithReponses2.ToModel(),
            _question.value.date
        )
    }

    override suspend fun postReponse(code: String, idJoueur: Int, idReponse: Int) {
        if (idReponse == 1) {
            _isResponseValid.value = true
        } else {
            _isResponseValid.value = false
        }
    }
}