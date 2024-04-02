package fr.iut.sciencequest.model.repositories.scientifique

import android.util.Log
import fr.iut.sciencequest.model.buisness.Scientifique.ScientifiqueRequestService
import fr.iut.sciencequest.model.buisness.createRequestService
import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.metier.Scientifique
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.create

class ScientifiqueAPIRepository: IScientifiqueRepository {
    suspend override fun fetchScientifiqueById(id: Int): Flow<Scientifique> = flow {
        val serviceClient = createRequestService().create<ScientifiqueRequestService>()
        try {
            val response =  serviceClient.getScientifique(id)
            emit(response.ToModel())
        } catch (e: Exception) {
            Log.e("Requete API",e.message.toString())
        }
    }

    suspend override fun fetchScientifiques(index: Int): Flow<List<Scientifique>> = flow {
        val serviceClient = createRequestService().create<ScientifiqueRequestService>()
        try {
            val response = serviceClient.getScientifiques(index)
            emit(response.scientifiques.ToModel())
        } catch (e: Exception) {
            Log.e("Requete API",e.message.toString())
        }
    }
}