package fr.iut.sciencequest.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.iut.sciencequest.viewModels.uiStates.ScientifiqueDecouvertsUIState
import fr.iut.sciencequest.model.repositories.scientifique.IScientifiqueRepository
import fr.iut.sciencequest.model.repositories.scientifique.ScientifiqueAPIRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ScientifiquesDecouvertsVM(
        val repository: IScientifiqueRepository
) : ViewModel() {
    private val _listeScientifique: MutableStateFlow<ScientifiqueDecouvertsUIState> = MutableStateFlow(ScientifiqueDecouvertsUIState())
    val listeScientifique = _listeScientifique.asStateFlow()

    fun getScientifiques(page: Int) {
        Log.d("ViewModelScientifique","Recup la liste de scientifiques")
        viewModelScope.launch {
            repository.fetchScientifiques(page).collect {
                _listeScientifique.value = ScientifiqueDecouvertsUIState(it.toMutableList())
            }
        }
    }
    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>
            ): T {
                return ScientifiquesDecouvertsVM(
                    ScientifiqueAPIRepository()
                ) as T
            }
        }
    }
 }