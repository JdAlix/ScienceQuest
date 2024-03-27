package fr.iut.sciencequest.model.buisness.Scientifique

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import fr.iut.sciencequest.model.dto.ScientifiqueDTOs.ScientifiqueDTO
import fr.iut.sciencequest.model.dto.ScientifiqueDTOs.ScientifiqueListDTO
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ScientifiqueRequestService {
    @GET("scientifiques?page")
    suspend fun getScientifiques(@Query("page") index: Int): ScientifiqueListDTO

    @GET("scientifiques/{id}")
    suspend fun getScientifique(@Path("id") id: Int): ScientifiqueDTO
}