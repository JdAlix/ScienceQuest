package fr.iut.sciencequest.model

import fr.iut.sciencequest.model.metier.Difficulte
import fr.iut.sciencequest.model.metier.Jeu
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class JeuTest (
    private val id: Int,
    private val nbParties: UInt,
    private val libelle: String
) {
    @Test
    fun constructorTest() {
        val jeu = Jeu(id,libelle,nbParties)

        Assert.assertEquals(id, jeu.id)
        Assert.assertEquals(libelle, jeu.nom)
        Assert.assertEquals(nbParties, jeu.nbrParties)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(
            name = "Creation d'un jeu d'id {0}, de nom {2} et avec {1} parties"
        )
        fun getTestActionData(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf(1, 0, ""),
                arrayOf(0, 0, ""),
                arrayOf(1, 1, "jeu"),
                arrayOf(0, 1, "un autre jeu")
            )
        }
    }
}