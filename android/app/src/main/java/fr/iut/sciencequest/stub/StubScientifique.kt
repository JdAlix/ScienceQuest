package fr.iut.sciencequest.stub

import fr.iut.sciencequest.model.dto.DifficulteDTO
import fr.iut.sciencequest.model.dto.ScientifiqueDTO
import fr.iut.sciencequest.model.dto.ThematiqueDTO

object StubScientifique1: ScientifiqueDTO (
    id = 1,
    descriptif = "description",
    nom = "Jean",
    prenom = "Jean",
    photo = "photo",
    sexe = 'H',
    difficulte = DifficulteDTO(1, "Facile"),
    thematique = ThematiqueDTO(1, "Nucléaire"),
    ratioTrouve = 0f
)

object StubScientifique2: ScientifiqueDTO (
    id = 2,
    descriptif = "autre description",
    nom = "Jean2",
    prenom = "Jean2",
    photo = "photo2",
    sexe = 'F',
    difficulte = DifficulteDTO(1, "Facile"),
    thematique = ThematiqueDTO(1, "Nucléaire"),
    ratioTrouve = 0f
)

fun getScientifiqueListeStub(): List<ScientifiqueDTO>{
    val liste = ArrayList<ScientifiqueDTO>()
    liste.add(StubScientifique2)
    liste.add(StubScientifique1)
    return liste
}