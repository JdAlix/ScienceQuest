package fr.iut.sciencequest.viewModel

import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.metier.Scientifique
import fr.iut.sciencequest.model.repositories.scientifique.ScientifiqueStubRepostory
import fr.iut.sciencequest.stub.StubScientifique1
import fr.iut.sciencequest.testRules.MainDispatcherRule
import fr.iut.sciencequest.viewModels.PenduViewModel
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

class PenduViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun TestInitPartie() {
        val scientifique = StubScientifique1.ToModel()
        val repo = ScientifiqueStubRepostory()
        repo.setScientifiqueStub(scientifique)
        val vm = PenduViewModel(repo)
        vm.InitPartie()
        val nomPrenom = scientifique.prenom + " " + scientifique.nom
        Assert.assertEquals(nomPrenom.length, vm.uiState.value.motATrou.length)
        val trou = nomPrenom.replace(regex = Regex("[A-Za-z]"),"_")
        Assert.assertEquals(trou, vm.uiState.value.motATrou)
    }

    @Test
    fun TestGoodActionPutsLetter() {
        val scientifique = StubScientifique1.ToModel()
        val repo = ScientifiqueStubRepostory()
        repo.setScientifiqueStub(scientifique)
        val vm = PenduViewModel(repo)
        vm.InitPartie()
        vm.PlayAction('e')
        Assert.assertEquals(true, vm.uiState.value.motATrou.contains('e'))
    }

    @Test
    fun TestWrongActionDoesNotPutLetter() {
        val scientifique = StubScientifique1.ToModel()
        val repo = ScientifiqueStubRepostory()
        repo.setScientifiqueStub(scientifique)
        val vm = PenduViewModel(repo)
        vm.InitPartie()
        vm.PlayAction('q')
        Assert.assertEquals(false, vm.uiState.value.motATrou.contains('e'))
    }
}