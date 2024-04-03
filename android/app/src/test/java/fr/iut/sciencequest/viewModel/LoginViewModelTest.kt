package fr.iut.sciencequest.viewModel

import fr.iut.sciencequest.testRules.MainDispatcherRule
import fr.iut.sciencequest.viewModels.LoginViewModel
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class LoginViewModelTest(
    val exempleString: String
) {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun setMdpTest() {
        val viewModel = LoginViewModel()

        viewModel.setMdp(exempleString)
        Assert.assertEquals(exempleString, viewModel.uiState.value.mdp)
    }

    @Test
    fun setPseudoTest() {
        val viewModel = LoginViewModel()

        viewModel.setPseudo(exempleString)
        Assert.assertEquals(exempleString, viewModel.uiState.value.pseudo)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(
            name = "Quand joueur joue {0}, le jeu est censé trouvé que {1} est {3} et il lui reste {2} "
        )
        fun getTestActionData(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf("Jean"),
                arrayOf("jean"),
                arrayOf("Jean2"),
                arrayOf("jean2"),
                arrayOf("jean_"),
                arrayOf("jean+"),
                arrayOf("jean+<<>"),
                arrayOf("jean;//;"),
            )
        }
    }
}