package fr.iut.sciencequest.model.repositories.question

import fr.iut.sciencequest.model.metier.question.QuestionWithSimpleReponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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