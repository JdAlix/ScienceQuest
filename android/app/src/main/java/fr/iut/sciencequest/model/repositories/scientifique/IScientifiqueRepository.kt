package fr.iut.sciencequest.model.repositories.scientifique

import fr.iut.sciencequest.model.metier.Scientifique
import fr.iut.sciencequest.model.metier.question.QuestionWithSimpleReponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface IScientifiqueRepository {

    val scientifique: StateFlow<Scientifique>
    val scientifiques: StateFlow<List<Scientifique>>
    suspend fun fetchScientifiques(index: Int)
    suspend fun fetchScientifiqueById(id: Int)
}