package fr.iut.sciencequest.model.dto.ScientifiqueDTOs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class ScientifiqueListInfosPageDTO(
    @SerialName("totalElements")
    val nbScientfiques: Int,

    @SerialName("totalPages")
    val nbPages: Int,

    @SerialName("size")
    val nbScientifiquesParPage: Int
)

@Serializable
open class ScientifiqueListInfosDTO (
    val page: ScientifiqueListInfosPageDTO
)