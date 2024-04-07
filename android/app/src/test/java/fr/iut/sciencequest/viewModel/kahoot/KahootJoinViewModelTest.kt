package fr.iut.sciencequest.viewModel.kahoot

import fr.iut.sciencequest.viewModels.KahootJoinViewModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class KahootJoinViewModelTest(
    val code: String
) {

    @Test
    fun TestSetGoodCode() {
        val vm = KahootJoinViewModel()
        vm.setCode(code)
        if (code.length <= 5) {
            Assert.assertEquals(code, vm.uiState.value.code)
        } else {
            Assert.assertEquals("", vm.uiState.value.code)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(
            name = "Quand le joueur rentre le code {0}"
        )
        fun getTestActionData(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf("AAA"),
                arrayOf("A"),
                arrayOf("AA"),
                arrayOf("A54"),
                arrayOf("B87AB"),
                arrayOf("B87ABaaa"),
                arrayOf("a7bB")
            )
        }
    }
}