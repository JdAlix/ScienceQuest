package fr.iut.sciencequest.model.buisness

import fr.iut.sciencequest.model.dto.ScientifiqueDTOs.ScientifiqueDTO
import fr.iut.sciencequest.model.dto.ScientifiqueDTOs.ScientifiqueListDTO
import fr.iut.sciencequest.model.dto.ScientifiqueDTOs.ScientifiqueListInfosDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ScientifiqueRequestService {
    @GET("scientifiques?page")
    suspend fun getScientifiques(@Query("page") index: Int): ScientifiqueListDTO

    @GET("scientifiques")
    suspend fun getScientifiquesListInfos(): ScientifiqueListInfosDTO

    @GET("scientifiques/{id}")
    suspend fun getScientifique(@Path("id") id: Int): ScientifiqueDTO
}