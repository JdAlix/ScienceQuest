package fr.iut.sciencequest.model

import fr.iut.sciencequest.model.metier.Thematique
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ThematiquesTest(
    val id: Int,
    val libelle: String
) {

    @Test
    fun constructorTest() {
        val thematique = Thematique(id,libelle)

        Assert.assertEquals(id, thematique.id)
        Assert.assertEquals(libelle, thematique.libelle)
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
                arrayOf(1,"thematique"),
                arrayOf(0,"une autre thematique")
            )
        }
    }
}