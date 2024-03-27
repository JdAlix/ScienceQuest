package fr.iut.sciencequest.ViewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.iut.sciencequest.model.buisness.Scientifique.fetchScientifiqueById
import fr.iut.sciencequest.model.buisness.Scientifique.fetchScientifiques
import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.metier.Scientifique
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ScientifiquesDecouvertsVM : ViewModel() {
    var listeScientifique: MutableStateFlow<MutableList<Scientifique>> = MutableStateFlow(ArrayList<Scientifique>().toMutableStateList())

    // fun getScientifiqueById(id: Int) {
    //     Log.d("ViewModelScientifique", "Recup un scientifique d'id: $id")
    //     var scientifique: Scientifique
    //     viewModelScope.launch {
//
    //     }
    // }
    fun getScientifiques(page: Int) {
        Log.d("ViewModelScientifique","Recup la liste de scientifiques")
        viewModelScope.launch {
            fetchScientifiques(page).collect {
                listeScientifique.value = it.scientifiques.ToModel().toMutableList()
            }
        }
    }
 }