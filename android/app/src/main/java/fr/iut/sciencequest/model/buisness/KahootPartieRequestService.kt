package fr.iut.sciencequest.model.buisness

import fr.iut.sciencequest.model.dto.partie.LaunchedPartieDTO
import fr.iut.sciencequest.model.dto.partie.PartieKahootDTO
import fr.iut.sciencequest.model.dto.partie.NouvellePartieDTO
import fr.iut.sciencequest.model.dto.question.QuestionPartieDTO
import fr.iut.sciencequest.model.dto.reponse.ReponseInfoDTO
import fr.iut.sciencequest.model.dto.reponseValidityDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface KahootPartieRequestService {
    @POST("partie/kahoot")
    suspend fun createPartie(@Body gameInfo: NouvellePartieDTO): PartieKahootDTO

    @POST("partie/kahoot/{codePartie}/demarrer")
    suspend fun launchPartie(@Path("codePartie") code: String): LaunchedPartieDTO

    @GET("partie/kahoot/{codePartie}/question")
    suspend fun getQuestionFromPartie(@Path("codePartie") code: String): QuestionPartieDTO

    @POST("partie/kahoot/{codeInvitation}/reponse")
    suspend fun postResponse(@Path("codeInvitation") code:String, @Body reponseInfo: ReponseInfoDTO): reponseValidityDTO
}