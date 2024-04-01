package fr.iut.sciencequest.ViewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import fr.iut.sciencequest.ViewModels.UiStates.ScientifiqueDecouvertsUIState
import fr.iut.sciencequest.model.buisness.Scientifique.fetchScientifiqueById
import fr.iut.sciencequest.model.buisness.Scientifique.fetchScientifiques
import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.metier.Scientifique
import fr.iut.sciencequest.model.repositories.scientifique.IScientifiqueRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ScientifiquesDecouvertsVM(
        val repository: IScientifiqueRepository,
        var listeScientifique: MutableStateFlow<ScientifiqueDecouvertsUIState> = MutableStateFlow(ScientifiqueDecouvertsUIState())
) : ViewModel() {

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
            repository.fetchScientifiques(page).collect {
                listeScientifique.value = ScientifiqueDecouvertsUIState(it.toMutableList())
            }
        }
    }
    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                    modelClass: Class<T>,
                    extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
                // Create a SavedStateHandle for this ViewModel from extras
                val savedStateHandle = extras.createSavedStateHandle()

                return ScientifiquesDecouvertsVM(

                ) as T
            }
        }
 }