package fr.iut.sciencequest.stub

import fr.iut.sciencequest.model.dto.ScientifiqueDTO

object StubScientifique1: ScientifiqueDTO (
    id = 1,
    descriptif = "description",
    nom = "Jean",
    prenom = "Jean",
    photo = "photo",
    sexe = 'H',
    difficulteId = 1,
    thematiqueId = 1,
    ratioTrouve = 0f
)
{}