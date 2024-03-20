package fr.iut.sciencequest.model.buisness

import android.util.Log
import fr.iut.sciencequest.model.dto.ScientifiqueDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.lang.IllegalArgumentException

fun fetchScientifiqueById(id: Int) {
    val serviceClient = createRequestService().create<ScientifiqueRequestService>()
    Log.d("Requete API","Fetch un scientifique")
    serviceClient.getScientifique(id).enqueue(
        object: Callback<ScientifiqueDTO> {
            override fun onResponse(
                call: Call<ScientifiqueDTO>,
                response: Response<ScientifiqueDTO>
            ) {
                // NOTE : il faudrait probablement utiliser une autre exception
                // exception personnalisée ?
                val data = response.body() ?:
                    throw IllegalArgumentException("ERREUR : l'api a donné une réponse vide")
                // Devrait appeler le ModelView, la méthode onResponse ne renvoit rien
                // Pour le moment des print pour vérifier que la requêtre fonctionne
                // sans avoir besoin des vues.
                Log.d("Requete API",data.id.toString())
                Log.d("Requete API", data.nom)
            }

            override fun onFailure(call: Call<ScientifiqueDTO>, t: Throwable) {
                Log.e("Requete API","Erreur lors de requete api")
                throw t
            }
        }
    )
}