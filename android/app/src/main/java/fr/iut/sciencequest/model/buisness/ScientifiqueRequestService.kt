package fr.iut.sciencequest.model.buisness

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import fr.iut.sciencequest.model.dto.ScientifiqueDTO
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

// a remplir
private const val API_BASE_URL = "https://sae-java.alix-jdlm.fr/api/v1/"

val httpClient = OkHttpClient()

interface ScientifiqueRequestService {
    @GET("scientifiques/{index}/{count}")
    fun getScientifiques(@Path("index") index: Int, @Path("count") count: Int): Call<List<ScientifiqueDTO>>

    @GET("scientifiques/{id}")
    fun getScientifique(@Path("id") id: Int): Call<ScientifiqueDTO>
}

fun createRequestService(): Retrofit =
    Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(Json {  ignoreUnknownKeys = true }.asConverterFactory(MediaType.get("application/json")))
        .client(httpClient)
        .build()