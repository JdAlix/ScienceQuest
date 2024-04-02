package fr.iut.sciencequest.model.repositories.scientifique

import android.util.Log
import fr.iut.sciencequest.model.buisness.Question.QuestionRequestService
import fr.iut.sciencequest.model.buisness.Scientifique.ScientifiqueRequestService
import fr.iut.sciencequest.model.buisness.createRequestService
import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.metier.Scientifique
import fr.iut.sciencequest.stub.StubScientifique1
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import retrofit2.create

class ScientifiqueAPIRepository: IScientifiqueRepository {

    private val _scientifique = MutableStateFlow(StubScientifique1.ToModel())
    override val scientifique: StateFlow<Scientifique>
        get() = _scientifique.asStateFlow()

    private val _scientifiques = MutableStateFlow<List<Scientifique>>(emptyList())
    override val scientifiques: StateFlow<List<Scientifique>>
        get() = _scientifiques.asStateFlow()

    override suspend fun fetchScientifiqueById(id: Int) {
        val serviceClient = createRequestService().create<ScientifiqueRequestService>()
        try {
            _scientifique.value = serviceClient.getScientifique(id).ToModel()
        } catch (e: Exception) {
            Log.e("Requete API Scientifiqu", e.message.toString())
        }
    }

    override suspend fun fetchScientifiques(index: Int) {
        val serviceClient = createRequestService().create<ScientifiqueRequestService>()
        try {
            _scientifiques.value = serviceClient.getScientifiques(index).scientifiques.ToModel()
        } catch (e: Exception) {
            Log.e("Requete API Scientifiqu", e.message.toString())
        }
    }
}