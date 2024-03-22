package fr.iut.sciencequest.model.dto.ScientifiqueDTOs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class ScientifiqueListDTO {
    @SerialName("_embedded")
    val scientifiques: List<ScientifiqueDTO>

    constructor(scientifiques: List<ScientifiqueDTO>) {
        this.scientifiques = scientifiques
    }
}