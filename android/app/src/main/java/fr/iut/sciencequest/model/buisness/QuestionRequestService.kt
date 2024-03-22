package fr.iut.sciencequest.model.buisness

import fr.iut.sciencequest.model.dto.question.QuestionListDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionRequestService {
    @GET("questions?page")
    fun getQuestion(@Query("page") index: Int): Call<QuestionListDTO>
}