package fr.iut.sciencequest.dto

import fr.iut.sciencequest.model.dto.ThematiqueDTO
import fr.iut.sciencequest.model.dto.difficulte.DifficulteDTO
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class DifficulteDTOTest (
    val id: Int,
    val libelle: String
) {
    @Test
    fun constructorTest() {
        val difficulte = DifficulteDTO(id,libelle)

        Assert.assertEquals(id, difficulte.id)
        Assert.assertEquals(libelle, difficulte.libelle)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(
            name = "Quand le joueur prend {0} ms pour faire son action, il est censé avoir {1} points"
        )
        fun getTestActionData(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf(1,""),
                arrayOf(0,""),
                arrayOf(1,"difficulte"),
                arrayOf(0,"une autre difficulte")
            )
        }
    }
}