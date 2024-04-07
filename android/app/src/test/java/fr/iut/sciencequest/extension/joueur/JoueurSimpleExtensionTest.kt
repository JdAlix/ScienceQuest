package fr.iut.sciencequest.extension.joueur

import fr.iut.sciencequest.model.dto.extensions.toModel
import fr.iut.sciencequest.model.dto.joueur.JoueurSimpleDTO
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class JoueurSimpleExtensionTest (
    val id: Int,
    val nom: String
) {
    @Test
    fun toModelTest() {
        val DTO = JoueurSimpleDTO(id,nom)
        val model = DTO.toModel()

        Assert.assertEquals(id, model.id)
        Assert.assertEquals(nom, model.pseudo)
    }

    @Test
    fun listetoModelTest() {
        val DTO = listOf(
            JoueurSimpleDTO(id,nom),
            JoueurSimpleDTO(id + 1,nom + "1"),
            JoueurSimpleDTO(id + 2,nom + "2")
        )
        val model = DTO.toModel()

        for (index in 0..model.size) {
            Assert.assertEquals(id + index, model[index].id)
            Assert.assertEquals(nom + index.toString(), model[index].pseudo)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(
            name = "Convertir dto to model"
        )
        fun getTestActionData(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf(1, ""),
                arrayOf(0, ""),
                arrayOf(1, "pseudo"),
                arrayOf(0, "un autre pseudo")
            )
        }
    }
}