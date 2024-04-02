package fr.iut.sciencequest.viewModel.pendu

import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.repositories.scientifique.ScientifiqueStubRepostory
import fr.iut.sciencequest.stub.StubScientifique1
import fr.iut.sciencequest.testRules.MainDispatcherRule
import fr.iut.sciencequest.viewModels.PenduViewModel
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class PenduJouerActionTest (
        private val playedLetter: Char,
        private val expectedLetter: Char,
        private val expectedVieRestante: Int,
        private val expectedResult: Boolean
){

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun TestGoodActionPutsLetter() {
        val scientifique = StubScientifique1.ToModel()
        val repo = ScientifiqueStubRepostory()
        repo.setScientifiqueStub(scientifique)
        val vm = PenduViewModel(repo)
        vm.InitPartie()
        vm.PlayAction(playedLetter)
        Assert.assertEquals(expectedResult, vm.uiState.value.motATrou.contains(expectedLetter))
        Assert.assertEquals(expectedVieRestante, vm.uiState.value.nbViesRestantes)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(
            name = "Quand joueur joue {0}, le jeu est censé trouvé que {1} est {2} "
        )
        fun getTestActionData(): Iterable<Array<Any>> {
            return arrayListOf(
                    arrayOf('e','e',10,true),
                    arrayOf('q','q',9,false),
                    arrayOf('E','e',10,true),
                    arrayOf('j','J',10,true),
                    arrayOf('J','J',10,true),
            )
        }
    }
}