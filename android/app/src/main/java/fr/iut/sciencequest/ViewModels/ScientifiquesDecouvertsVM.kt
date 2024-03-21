package fr.iut.sciencequest.ViewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import fr.iut.sciencequest.model.metier.Scientifique

class ScientifiquesDecouvertsVM : ViewModel() {
    private var listeScientifique: MutableList<Scientifique> = mutableStateListOf<Scientifique>()

    fun getScientifiques(): MutableList<Scientifique> {
        Log.d("ViewModel","""je get un scientifique, taille act : ${listeScientifique.size}""")
        return listeScientifique
    }

    fun addScientifiques(scientifique: Scientifique) {
        if (listeScientifique.add(scientifique))  {
            Log.d("ViewModel","""j'ajoute un scientifique, taille act : ${listeScientifique.size}""")
        } else {
            Log.d("ViewModel","Erreur lors d'un ajout")
        }
    }
 }