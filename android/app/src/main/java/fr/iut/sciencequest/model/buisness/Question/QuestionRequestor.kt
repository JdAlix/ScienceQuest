package fr.iut.sciencequest.model.buisness.Question

import android.util.Log
import fr.iut.sciencequest.model.buisness.Scientifique.ScientifiqueRequestService
import fr.iut.sciencequest.model.buisness.createRequestService
import fr.iut.sciencequest.model.dto.question.QuestionListDTO
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.lang.IllegalArgumentException

suspend fun fetchQuestions(index: Int) = flow {
    val serviceClient = createRequestService().create<QuestionRequestService>()
    try {
        val response =  serviceClient.getQuestions(index)
        emit(response)
    } catch (e: Exception) {
        Log.e("Requete API Question",e.message.toString())
    }
}
