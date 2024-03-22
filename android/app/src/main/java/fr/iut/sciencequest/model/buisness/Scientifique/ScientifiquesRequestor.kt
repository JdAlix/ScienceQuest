package fr.iut.sciencequest.model.buisness.Scientifique

import android.util.Log
import fr.iut.sciencequest.model.buisness.createRequestService
import fr.iut.sciencequest.model.dto.ScientifiqueDTOs.ScientifiqueDTO
import fr.iut.sciencequest.model.dto.ScientifiqueDTOs.ScientifiqueListDTO
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
                Log.e("Requete API","Erreur lors d'une requete api")
                throw t
            }
        }
    )
}

fun fetchScientifiques(index: Int) {
    val serviceClient = createRequestService().create<ScientifiqueRequestService>()
    Log.d("Requete API","Fetch plusieurs scientifiques")
    serviceClient.getScientifiques(index).enqueue(
        object: Callback<ScientifiqueListDTO> {
            override fun onResponse(
                call: Call<ScientifiqueListDTO>,
                response: Response<ScientifiqueListDTO>
            ) {
                // NOTE : il faudrait probablement utiliser une autre exception
                // exception personnalisée ?
                val data = response.body() ?:
                    throw IllegalArgumentException("ERREUR : l'api a donné une réponse vide")
                // Devrait appeler le ModelView, la méthode onResponse ne renvoit rien
                // Pour le moment des print pour vérifier que la requêtre fonctionne
                // sans avoir besoin des vues.
                for (scientifique in data.scientifiques) {

                    Log.d("Requete API",scientifique.id.toString())
                    Log.d("Requete API", scientifique.nom)
                }
            }

            override fun onFailure(call: Call<ScientifiqueListDTO>, t: Throwable) {
                Log.e("Requete API","Erreur lors d'une requete api")
                throw t
            }
        }
    )
}