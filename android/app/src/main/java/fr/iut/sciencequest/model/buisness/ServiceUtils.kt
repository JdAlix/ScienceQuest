package fr.iut.sciencequest.model.buisness

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

const val API_BASE_URL = "https://codefirst.iut.uca.fr/containers/tombiard-api/api/v1/"

val httpClient = OkHttpClient()

fun createRequestService(): Retrofit =
    Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(Json {  ignoreUnknownKeys = true }.asConverterFactory(MediaType.get("application/json")))
        .client(httpClient)
        .build()