package fr.iut.sciencequest.extension

import fr.iut.sciencequest.model.dto.ScientifiqueDTOs.ScientifiqueDTO
import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.dto.question.QuestionDTO
import fr.iut.sciencequest.model.dto.reponse.ReponseDTO
import fr.iut.sciencequest.model.metier.Scientifique
import fr.iut.sciencequest.model.metier.question.Question
import fr.iut.sciencequest.model.metier.reponse.Reponse
import fr.iut.sciencequest.stub.StubScientifique1
import fr.iut.sciencequest.stub.StubScientifique2
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ReponseDTOToModelTest (
    val id: Int,
    val reponse: String,
    val question: QuestionDTO,
    val scientifique: ScientifiqueDTO
) {
    @Test
    fun toModelTest() {
        val DTO = ReponseDTO(id,reponse,question,scientifique)
        val model = DTO.ToModel()

        Assert.assertEquals(id, model.id)
        Assert.assertEquals(reponse, model.reponse)
        Assert.assertEquals(question.id, model.question.id)
        Assert.assertEquals(scientifique.id, model.scientifique.id)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(
            name = "Convertir dto to model"
        )
        fun getTestActionData(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf(1, "", QuestionDTO(1,"", emptyList()),StubScientifique1),
                arrayOf(0, "", QuestionDTO(1,"", emptyList()),StubScientifique2),
                arrayOf(1, "reponse",QuestionDTO(2,"", emptyList()),StubScientifique1),
                arrayOf(0, "une autre reponse",QuestionDTO(2,"", emptyList()),StubScientifique2)
            )
        }
    }
}