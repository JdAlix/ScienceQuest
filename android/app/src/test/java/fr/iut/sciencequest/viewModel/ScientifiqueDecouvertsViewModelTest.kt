package fr.iut.sciencequest.viewModel

import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.metier.Scientifique
import fr.iut.sciencequest.model.repositories.scientifique.ScientifiqueStubRepostory
import fr.iut.sciencequest.stub.StubScientifique1
import fr.iut.sciencequest.stub.StubScientifique2
import fr.iut.sciencequest.testRules.MainDispatcherRule
import fr.iut.sciencequest.viewModels.PenduViewModel
import fr.iut.sciencequest.viewModels.ScientifiquesDecouvertsVM
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ScientifiqueDecouvertsViewModelTest(
    val scientifiques: MutableList<Scientifique>
) {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun getScientifiqueTest() {
        val repo = ScientifiqueStubRepostory()
        repo.setScientifiqueStubList(scientifiques)
        val vm = ScientifiquesDecouvertsVM(repo)
        vm.getScientifiques(1)
        for (scientifique in scientifiques) {
            Assert.assertEquals(true, vm.listeScientifique.value.scientifiques.contains(scientifique))
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(
            name = "Quand on ajoute {O}"
        )
        fun getTestActionData(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf(mutableListOf(
                    StubScientifique1.ToModel()
                )),
                arrayOf(mutableListOf(
                    StubScientifique2.ToModel()
                )),
                arrayOf(mutableListOf(
                    StubScientifique1.ToModel(),
                    StubScientifique2.ToModel()
                )),
            )
        }
    }
}