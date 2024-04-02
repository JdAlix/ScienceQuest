package fr.iut.sciencequest.viewModel.kahoot

import android.os.Looper
import androidx.compose.runtime.collectAsState
import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.repositories.question.QuestionStubRepository
import fr.iut.sciencequest.stub.StubQuestionWithReponses
import fr.iut.sciencequest.stub.StubQuestionWithReponses2
import fr.iut.sciencequest.testRules.MainDispatcherRule
import fr.iut.sciencequest.viewModels.KahootViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.Duration

class KahootLancerPartieTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        mockkStatic(Looper::class)

        val looper = mockk<Looper> {
            every { thread } returns Thread.currentThread()
        }

        every { Looper.getMainLooper() } returns looper
    }

    @Test
    fun KahootLancerPartieTest() = runTest (timeout = Duration.parse("15s")) {
        val question = StubQuestionWithReponses.ToModel()
        val repo = QuestionStubRepository()
        repo.setQuestionsStub(arrayListOf(
                StubQuestionWithReponses.ToModel(),
                StubQuestionWithReponses2.ToModel()
        ))
        val viewModel = KahootViewModel(repo)
        Assert.assertEquals(StubQuestionWithReponses.id, viewModel.uiState.value.question.id)
        Assert.assertEquals(StubQuestionWithReponses.question, viewModel.uiState.value.question.question)
        Assert.assertEquals(StubQuestionWithReponses.reponses.count(), viewModel.uiState.value.question.reponses.count())
        // On simule l'attente de la fin de la question
        Thread.sleep(12_000)
        Assert.assertEquals(StubQuestionWithReponses2.id, viewModel.uiState.value.question.id)
        Assert.assertEquals(StubQuestionWithReponses2.question, viewModel.uiState.value.question.question)
        Assert.assertEquals(StubQuestionWithReponses2.reponses.count(), viewModel.uiState.value.question.reponses.count())
    }
}