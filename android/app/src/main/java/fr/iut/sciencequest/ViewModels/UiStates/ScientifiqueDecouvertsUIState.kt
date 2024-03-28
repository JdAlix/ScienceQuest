package fr.iut.sciencequest.ViewModels.UiStates

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