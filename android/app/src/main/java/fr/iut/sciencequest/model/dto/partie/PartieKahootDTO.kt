package fr.iut.sciencequest.model.dto.partie

import fr.iut.sciencequest.model.dto.ThematiqueDTO
import fr.iut.sciencequest.model.dto.difficulte.DifficulteDTO
import fr.iut.sciencequest.model.dto.joueur.JoueurSimpleDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PartieKahootDTO (
    val id: Int,
    @SerialName("codeInvitation")
    val code: String,
    val thematiques: List<ThematiqueDTO>,
    val joueurs: List<JoueurSimpleDTO>,
    val difficulte: DifficulteDTO
)