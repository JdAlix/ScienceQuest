package fr.iut.sciencequest.dto

import fr.iut.sciencequest.model.dto.ScientifiqueDTOs.ScientifiqueDTO
import fr.iut.sciencequest.model.dto.ThematiqueDTO
import fr.iut.sciencequest.model.dto.difficulte.DifficulteDTO
import fr.iut.sciencequest.model.metier.Scientifique
import kotlinx.serialization.SerialName
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ScientifiqueDTOTest(
    val id: Int,
    val nom: String,
    val prenom: String,
    val photo: String,
    val descriptif: String,
    val ratioTrouve: Float,
    val sexe: Char,
    val difficulte : DifficulteDTO,
    val thematique : ThematiqueDTO
) {
    @Test
    fun constructorTest() {
        val scientifique = ScientifiqueDTO(
            id,
            nom,
            prenom,
            photo,
            descriptif,
            ratioTrouve,
            sexe,
            difficulte,
            thematique
        )

        Assert.assertEquals(id, scientifique.id)
        Assert.assertEquals(nom, scientifique.nom)
        Assert.assertEquals(nom, scientifique.nom)
        Assert.assertEquals(photo, scientifique.photo)
        Assert.assertEquals(descriptif, scientifique.descriptif)
        Assert.assertEquals(ratioTrouve, scientifique.ratioTrouve)
        Assert.assertEquals(sexe, scientifique.sexe)
        Assert.assertEquals(difficulte.id, scientifique.difficulte.id)
        Assert.assertEquals(thematique.id, scientifique.thematique.id)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(
            name = "Quand le joueur prend {0} ms pour faire son action, il est censé avoir {1} points"
        )
        fun getTestActionData(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf(1,"nom","prenom","lienphoto","descriptif",0,'F',DifficulteDTO(1,"difficulte"),ThematiqueDTO(1,"thematique")),
                arrayOf(2,"nom","prenom","lienphoto","descriptif",0,'F',DifficulteDTO(1,"difficulte"),ThematiqueDTO(1,"thematique")),
                arrayOf(1,"autre nom","prenom","lienphoto","descriptif",0,'F',DifficulteDTO(1,"difficulte"),ThematiqueDTO(1,"thematique")),
                arrayOf(1,"nom","autre prenom","lienphoto","descriptif",0,'F',DifficulteDTO(1,"difficulte"),ThematiqueDTO(1,"thematique")),
                arrayOf(1,"nom","prenom","autre lienphoto","descriptif",0,'F',DifficulteDTO(1,"difficulte"),ThematiqueDTO(1,"thematique")),
                arrayOf(1,"nom","prenom","lienphoto","autre descriptif",1,'M',DifficulteDTO(1,"difficulte"),ThematiqueDTO(1,"thematique")),
                arrayOf(1,"nom","prenom","lienphoto","descriptif",0,'F',DifficulteDTO(2,"difficulte"),ThematiqueDTO(1,"thematique")),
                arrayOf(1,"nom","prenom","lienphoto","descriptif",0,'F',DifficulteDTO(1,"autre difficulte"),ThematiqueDTO(1,"thematique")),
                arrayOf(1,"nom","prenom","lienphoto","descriptif",0,'F',DifficulteDTO(1,"difficulte"),ThematiqueDTO(2,"thematique")),
                arrayOf(1,"nom","prenom","lienphoto","descriptif",0,'F',DifficulteDTO(1,"difficulte"),ThematiqueDTO(1,"autre thematique"))
            )
        }
    }
}