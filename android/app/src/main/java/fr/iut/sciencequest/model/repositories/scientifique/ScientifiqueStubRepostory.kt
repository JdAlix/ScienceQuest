package fr.iut.sciencequest.model.repositories.scientifique

import android.content.res.Resources.NotFoundException
import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.metier.Scientifique
import fr.iut.sciencequest.stub.StubScientifique1
import fr.iut.sciencequest.stub.StubScientifique2
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScientifiqueStubRepostory : IScientifiqueRepository {

    private var listeStub : MutableList<Scientifique> = mutableListOf(
        StubScientifique1.ToModel(),
        StubScientifique2.ToModel()
    )
    private val _scientifique = MutableStateFlow(StubScientifique1.ToModel())
    override val scientifique: StateFlow<Scientifique>
        get() = _scientifique.asStateFlow()
    private val _scientifiques = MutableStateFlow<List<Scientifique>>(emptyList())
    override val scientifiques: StateFlow<List<Scientifique>>
        get() = _scientifiques.asStateFlow()

    override suspend fun fetchScientifiques(index: Int) {
        _scientifiques.value = listeStub
    }

    override suspend fun fetchScientifiqueById(id: Int) {
        val retrieved = listeStub.find {
            it.id == id
        } ?: throw NotFoundException("Scientifique introuvable dans le stub")
        _scientifique.value = retrieved
    }

    fun setScientifiqueStubList(scientifiques: MutableList<Scientifique>) {
        listeStub = scientifiques
    }
}