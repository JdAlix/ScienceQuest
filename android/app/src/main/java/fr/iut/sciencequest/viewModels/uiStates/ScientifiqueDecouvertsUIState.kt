package fr.iut.sciencequest.viewModels.uiStates

import fr.iut.sciencequest.model.metier.Scientifique

class ScientifiqueDecouvertsUIState {
    var scientifiques: MutableList<Scientifique>

    constructor() {
        scientifiques = ArrayList<Scientifique>().toMutableList()
    }

    constructor(scientifiques : MutableList<Scientifique>) {
        this.scientifiques = scientifiques
    }
}