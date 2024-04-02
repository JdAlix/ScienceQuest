package fr.iut.sciencequest.model.buisness.Scientifique

import android.util.Log
import fr.iut.sciencequest.model.buisness.createRequestService
import kotlinx.coroutines.flow.flow
import retrofit2.create

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

fun fetchScientifiquesInfos() = flow {
    val serviceClient = createRequestService().create<ScientifiqueRequestService>()
    try {
        val response = serviceClient.getScientifiquesListInfos().page
        emit(response)
    } catch (e: Exception) {
        Log.e("Requete API",e.message.toString())
    }
}