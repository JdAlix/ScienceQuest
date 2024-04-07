package fr.iut.sciencequest.model.repositories.kahootPartie

import android.util.Log
import fr.iut.sciencequest.model.buisness.KahootPartieRequestService
import fr.iut.sciencequest.model.buisness.createRequestService
import fr.iut.sciencequest.model.dto.extensions.toModel
import fr.iut.sciencequest.model.dto.partie.NouvellePartieDTO
import fr.iut.sciencequest.model.dto.reponse.ReponseInfoDTO
import fr.iut.sciencequest.model.metier.Difficulte
import fr.iut.sciencequest.model.metier.partie.PartieKahoot
import fr.iut.sciencequest.model.metier.question.QuestionPartie
import fr.iut.sciencequest.model.metier.question.QuestionWithSimpleReponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import retrofit2.create

class KahootAPIRepository: IKahootPartieRepository {

    private val _partie = MutableStateFlow(PartieKahoot(0,"", emptyList(),emptyList(), Difficulte(0,"")))
    override val partie: StateFlow<PartieKahoot>
        get() = _partie.asStateFlow()

    private val _question = MutableStateFlow(QuestionPartie(QuestionWithSimpleReponse(0,"", emptyList()), LocalDateTime(1,1,1,1,1 ).toInstant(
        TimeZone.UTC)))
    override val question: StateFlow<QuestionPartie>
        get()  = _question.asStateFlow()

    private val _isStarted = MutableStateFlow(false)
    override val isStarted: StateFlow<Boolean>
        get() = _isStarted.asStateFlow()

    private val _isValidResponse = MutableStateFlow(false)
    override val isReponseValid: StateFlow<Boolean>
        get() = _isValidResponse.asStateFlow()

    override suspend fun createPartie(nvPartie: NouvellePartieDTO) {
        val serviceClient = createRequestService().create<KahootPartieRequestService>()
        try {
            _partie.value = serviceClient.createPartie(nvPartie).toModel()
            Log.d("Req partie crea",_partie.value.code)
        } catch (e: Exception) {
            Log.e("Requete API Partie crea", e.message.toString())
        }
    }

    override suspend fun startPartie(code: String) {
        val serviceClient = createRequestService().create<KahootPartieRequestService>()
        try {
            val partieStatus = serviceClient.launchPartie(code)
            _isStarted.value = partieStatus.status == "Started"
        } catch (e: Exception) {
            Log.e("Requete API Partie star", e.message.toString())
        }
    }

    override suspend fun getQuestion(code: String) {
        val serviceClient = createRequestService().create<KahootPartieRequestService>()
        try {
            _question.value = serviceClient.getQuestionFromPartie(code).toModel()
        } catch (e: Exception) {
            Log.e("Requete API Partie getq", e.message.toString())
            _question.value = QuestionPartie(
                question = _question.value.question,
                date = LocalDateTime(1,1,1,1,1,1,1).toInstant(TimeZone.UTC)
            )
        }
    }

    override suspend fun postReponse(code: String, idJoueur: Int, idReponse: Int) {
        val serviceClient = createRequestService().create<KahootPartieRequestService>()
        try {
            _isValidResponse.value = serviceClient.postResponse(code, ReponseInfoDTO(idJoueur, idReponse)).isValid
        } catch (e: Exception) {
            Log.e("Requete API Partie post", e.message.toString())
        }
    }
}