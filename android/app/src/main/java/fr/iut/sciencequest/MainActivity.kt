package fr.iut.sciencequest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import fr.iut.sciencequest.ViewModels.ScientifiquesDecouvertsVM
import fr.iut.sciencequest.model.buisness.fetchScientifiqueById
import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.navigation.NavHost
import fr.iut.sciencequest.stub.StubScientifique1
import fr.iut.sciencequest.ui.theme.ScienceQuestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScienceQuestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost()
                }
            }
        }
    }
}