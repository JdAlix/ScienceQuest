package fr.iut.sciencequest.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class reponseValidityDTO (
    @SerialName("estValide")
    val isValid: Boolean
)