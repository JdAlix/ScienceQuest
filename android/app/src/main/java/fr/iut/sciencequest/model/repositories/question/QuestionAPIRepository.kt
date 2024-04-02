package fr.iut.sciencequest.model.repositories.question

import android.util.Log
import fr.iut.sciencequest.model.buisness.Question.QuestionRequestService
import fr.iut.sciencequest.model.buisness.createRequestService
import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.metier.question.Question
import fr.iut.sciencequest.model.metier.question.QuestionWithSimpleReponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import retrofit2.create

class QuestionAPIRepository : IQuestionRepository {

    private val _questions = MutableStateFlow<List<QuestionWithSimpleReponse>>(emptyList())
    override val questions: StateFlow<List<QuestionWithSimpleReponse>>
        get() = _questions.asStateFlow()

    override suspend fun fetchQuestions(index: Int) {
        //1. appel à l'api QuestionRequestService
        //2. fill _questions with QuestionRequestService response
//        _questions.update {
//
//        }
        val serviceClient = createRequestService().create<QuestionRequestService>()
        try {
            _questions.value = serviceClient.getQuestions(index).questions.ToModel()
        } catch (e: Exception) {
            Log.e("Requete API Question", e.message.toString())
        }
    }
}