package fr.iut.sciencequest.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScientifiqueDTO (
    val id: Int,
    val nom: String,
    val prenom: String,
    @SerialName("pathToPhoto")
    val photo: String,
    val descriptif: String,
    val ratioTrouve: Float,
    val sexe: Char,
    val difficulte : DifficulteDTO,
    val thematique : ThematiqueDTO
)