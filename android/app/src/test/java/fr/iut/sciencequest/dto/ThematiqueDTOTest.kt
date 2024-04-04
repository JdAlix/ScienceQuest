package fr.iut.sciencequest.dto

import android.os.Looper
import fr.iut.sciencequest.model.dto.ThematiqueDTO
import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.repositories.question.QuestionStubRepository
import fr.iut.sciencequest.stub.StubQuestionWithReponses
import fr.iut.sciencequest.testRules.MainDispatcherRule
import fr.iut.sciencequest.viewModels.KahootViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ThematiqueDTOTest(
    val id: Int,
    val libelle: String
) {

    @Test
    fun constructorTest() {
        val thematique = ThematiqueDTO(id,libelle)

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