package fr.iut.sciencequest.model.repositories.question

import android.util.Log
import fr.iut.sciencequest.model.buisness.Question.QuestionRequestService
import fr.iut.sciencequest.model.buisness.createRequestService
import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.metier.question.Question
import fr.iut.sciencequest.model.metier.question.QuestionWithSimpleReponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.create

class QuestionStubRepository: IQuestionRepository {

    private val _questions = MutableStateFlow<List<QuestionWithSimpleReponse>>(emptyList())
    override val questions: StateFlow<List<QuestionWithSimpleReponse>>
        get() = _questions.asStateFlow()

    // NOTE : la méthode fait volontairement rien,
    // Il faut override mais le scientifique est déjà set
    // avec la méthode setScientifiqueStubList
    // Et hors contexte de test, cette implémentation ne
    // sert à rien
    override suspend fun fetchQuestions(index: Int) {

    }

    fun setQuestionsStub(questions: List<QuestionWithSimpleReponse>) {
        _questions.value = questions
    }
}