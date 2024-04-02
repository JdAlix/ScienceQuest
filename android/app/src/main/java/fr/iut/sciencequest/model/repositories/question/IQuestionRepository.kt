package fr.iut.sciencequest.model.repositories.question

import fr.iut.sciencequest.model.metier.question.Question
import fr.iut.sciencequest.model.metier.question.QuestionWithSimpleReponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface IQuestionRepository {
    val questions: StateFlow<List<QuestionWithSimpleReponse>>
    suspend fun fetchQuestions(index: Int)
}