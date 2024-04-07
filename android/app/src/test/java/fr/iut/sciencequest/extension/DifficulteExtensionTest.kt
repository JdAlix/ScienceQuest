package fr.iut.sciencequest.extension

import fr.iut.sciencequest.model.dto.difficulte.DifficulteDTO
import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.dto.question.QuestionDTO
import fr.iut.sciencequest.model.dto.reponse.ReponseDTO
import fr.iut.sciencequest.model.metier.Difficulte
import fr.iut.sciencequest.stub.StubScientifique1
import fr.iut.sciencequest.stub.StubScientifique2
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class DifficulteExtensionTest (
    val id: Int,
    val libelle: String
) {
    @Test
    fun toModelTest() {
        val DTO = DifficulteDTO(id,libelle)
        val model = DTO.ToModel()

        Assert.assertEquals(id, model.id)
        Assert.assertEquals(libelle, model.libelle)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(
            name = "Convertir dto to model"
        )
        fun getTestActionData(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf(1, ""),
                arrayOf(0, ""),
                arrayOf(1, "reponse"),
                arrayOf(0, "une autre reponse")
            )
        }
    }
}