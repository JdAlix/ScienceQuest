package fr.iut.sciencequest.model.repositories.question

import android.util.Log
import fr.iut.sciencequest.model.buisness.QuestionRequestService
import fr.iut.sciencequest.model.buisness.createRequestService
import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.metier.question.QuestionWithSimpleReponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.create

class QuestionAPIRepository : IQuestionRepository {

    private val _questions = MutableStateFlow<List<QuestionWithSimpleReponse>>(emptyList())
    override val questions: StateFlow<List<QuestionWithSimpleReponse>>
        get() = _questions.asStateFlow()

    override suspend fun fetchQuestions(index: Int) {
        val serviceClient = createRequestService().create<QuestionRequestService>()
        try {
            _questions.value = serviceClient.getQuestions(index).questions.ToModel()
        } catch (e: Exception) {
            Log.e("Requete API Question", e.message.toString())
        }
    }
}