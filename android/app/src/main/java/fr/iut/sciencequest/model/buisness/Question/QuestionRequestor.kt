package fr.iut.sciencequest.model.buisness.Question

import android.util.Log
import fr.iut.sciencequest.model.buisness.createRequestService
import fr.iut.sciencequest.model.dto.question.QuestionListDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.lang.IllegalArgumentException

fun fetchQuestions(index: Int) {
    val serviceClient = createRequestService().create<QuestionRequestService>()
    Log.d("Requete API","Fetch un scientifique")
    serviceClient.getQuestion(index).enqueue(
        object: Callback<QuestionListDTO> {
            override fun onResponse(
                call: Call<QuestionListDTO>,
                response: Response<QuestionListDTO>
            ) {
                // NOTE : il faudrait probablement utiliser une autre exception
                // exception personnalisée ?
                val data = response.body() ?:
                throw IllegalArgumentException("ERREUR : l'api a donné une réponse vide")
                // Devrait appeler le ModelView, la méthode onResponse ne renvoit rien
                // Pour le moment des print pour vérifier que la requêtre fonctionne
                // sans avoir besoin des vues.
                for (question in data.questions) {
                    Log.d("Requete Question", "id question: " + question.id.toString())
                    Log.d("Requete Question","Libelle: " + question.question)
                    Log.d("Requete Question","Reponses: ")
                    for (reponse in question.reponses) {
                        Log.d("Requete Question","id: " + reponse.id)
                        Log.d("Requete Question","Libelle: " + reponse.reponse)
                    }
                }
            }

            override fun onFailure(call: Call<QuestionListDTO>, t: Throwable) {
                Log.e("Requete API","Erreur lors d'une requete api")
                throw t
            }
        }
    )
}