package fr.iut.sciencequest.model.repositories.scientifique

import fr.iut.sciencequest.model.metier.Scientifique
import kotlinx.coroutines.flow.Flow

interface IScientifiqueRepository {
    suspend fun fetchScientifiques(index: Int): Flow<List<Scientifique>>
    suspend fun fetchScientifiqueById(id: Int): Flow<Scientifique>
}