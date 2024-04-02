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

    private val _scientifique = MutableStateFlow(StubScientifique1.ToModel())
    override val scientifique: StateFlow<Scientifique>
        get() = _scientifique.asStateFlow()
    private val _scientifiques = MutableStateFlow<List<Scientifique>>(emptyList())
    override val scientifiques: StateFlow<List<Scientifique>>
        get() = _scientifiques.asStateFlow()

    // NOTE : la méthode fait volontairement rien,
    // Il faut override mais le scientifique est déjà set
    // avec la méthode setScientifiqueStubList
    // Et hors contexte de test, cette implémentation ne
    // sert à rien
    override suspend fun fetchScientifiques(index: Int) {

    }

    // NOTE : la méthode fait volontairement rien,
    // Il faut override mais le scientifique est déjà set
    // avec la méthode setScientifiqueStub
    // Et hors contexte de test, cette implémentation ne
    // sert à rien
    override suspend fun fetchScientifiqueById(id: Int) {

    }

    fun setScientifiqueStubList(scientifiques: MutableList<Scientifique>) {
        _scientifiques.value = scientifiques
    }

    fun setScientifiqueStub(scientifique: Scientifique) {
        _scientifique.value = scientifique
    }
}