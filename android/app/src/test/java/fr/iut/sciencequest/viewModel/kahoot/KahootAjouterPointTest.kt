package fr.iut.sciencequest.viewModel.kahoot

import android.os.Looper
import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.repositories.question.QuestionStubRepository
import fr.iut.sciencequest.stub.StubQuestionWithReponses
import fr.iut.sciencequest.stub.StubQuestionWithReponses2
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
class KahootAjouterPointTest(
    val duree: Int,
    val expectedPoints: Int
) {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    lateinit var viewModel: KahootViewModel

    @Before
    fun init() {
        mockkStatic(Looper::class)

        val looper = mockk<Looper> {
            every { thread } returns Thread.currentThread()
        }

        every { Looper.getMainLooper() } returns looper
        val repo = QuestionStubRepository()
        repo.setQuestionsStub(arrayListOf(
            StubQuestionWithReponses.ToModel()
        ))
        viewModel = KahootViewModel(repo)
    }

    @Test
    fun ajouterPointsJustes() {
        viewModel.ajouterPoints(duree.toLong())
        Assert.assertEquals(expectedPoints, viewModel.uiState.value.nbPoints)
    }

    @Test
    fun ajouterPointsAvecTempsNegatif() {
        Assert.assertThrows(IllegalArgumentException::class.java) {
            viewModel.ajouterPoints(-1)
        }
    }

    @Test
    fun ajouterPointsAvecTempsTropLent() {
        Assert.assertThrows(IllegalArgumentException::class.java) {
            viewModel.ajouterPoints(11_000)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(
            name = "Quand le joueur prend {0} ms pour faire son action, il est censé avoir {1} points"
        )
        fun getTestActionData(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf(0,10_000),
                arrayOf(2_500,7_500),
                arrayOf(5_000,5_000),
                arrayOf(7_500,2_500),
                arrayOf(10_000,0)
            )
        }
    }
}