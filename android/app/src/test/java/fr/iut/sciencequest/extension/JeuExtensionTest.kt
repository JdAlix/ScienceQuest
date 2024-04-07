package fr.iut.sciencequest.extension

import fr.iut.sciencequest.model.dto.JeuDTO
import fr.iut.sciencequest.model.dto.difficulte.DifficulteDTO
import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.dto.extensions.toModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class JeuExtensionTest  (
    val id: Int,
    val nbPartie: UInt,
    val libelle: String
) {
    @Test
    fun toModelTest() {
        val DTO = JeuDTO(id,libelle,nbPartie)
        val model = DTO.toModel()

        Assert.assertEquals(id, model.id)
        Assert.assertEquals(libelle, model.nom)
        Assert.assertEquals(nbPartie, model.nbrParties)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(
            name = "Convertir dto to model"
        )
        fun getTestActionData(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf(1, 1, ""),
                arrayOf(0, 1, ""),
                arrayOf(1, 2, "reponse"),
                arrayOf(0, 2, "une autre reponse")
            )
        }
    }
}