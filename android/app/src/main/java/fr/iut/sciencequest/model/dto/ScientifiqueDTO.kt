package fr.iut.sciencequest.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScientifiqueDTO (
    val id: Int,
    val nom: String,
    val prenom: String,
    val photo: String,
    val descriptif: String,
    @SerialName("ratiotrouve")
    val ratioTrouve: Float,
    val sexe: Char,
    val difficulteId: Int,
    val thematiqueId: Int
)