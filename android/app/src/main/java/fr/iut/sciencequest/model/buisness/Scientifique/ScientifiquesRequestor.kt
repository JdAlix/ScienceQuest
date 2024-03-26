package fr.iut.sciencequest.model.buisness.Scientifique

import android.util.Log
import fr.iut.sciencequest.model.buisness.createRequestService
import fr.iut.sciencequest.model.dto.ScientifiqueDTOs.ScientifiqueDTO
import fr.iut.sciencequest.model.dto.ScientifiqueDTOs.ScientifiqueListDTO
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.lang.IllegalArgumentException

suspend fun fetchScientifiqueById(id: Int) = flow {
    val serviceClient = createRequestService().create<ScientifiqueRequestService>()
    try {
        val response =  serviceClient.getScientifique(id)
        emit(response)
    } catch (e: Exception) {
        Log.e("Requete API",e.message.toString())
    }
}

fun fetchScientifiques(index: Int) = flow {
    val serviceClient = createRequestService().create<ScientifiqueRequestService>()
    try {
        val response = serviceClient.getScientifiques(index)
        emit(response)
    } catch (e: Exception) {
        Log.e("Requete API",e.message.toString())
    }
}