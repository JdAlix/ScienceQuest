package fr.iut.sciencequest.view.games.kahoot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.iut.sciencequest.R
import fr.iut.sciencequest.model.metier.joueur.JoueurSimple
import fr.iut.sciencequest.view.TopBar
import fr.iut.sciencequest.viewModels.KahootJoinViewModel
import fr.iut.sciencequest.viewModels.KahootViewModel

@Composable
fun WaitingScreen(kahootViewModel: KahootViewModel = viewModel(factory = KahootViewModel.ApiFactory),
                  goToAccount: () -> Unit,
                  goToHome: () -> Unit,
                  goToGame: () -> Unit) {
    val state = kahootViewModel.uiState.collectAsState()
    Column(modifier = Modifier.fillMaxWidth(),
           horizontalAlignment = Alignment.CenterHorizontally) {
        TopBar(goToAccount, goToHome, stringResource(id = R.string.kahoot))
        Column {
            Text(text = stringResource(id = R.string.user_in_game))
            ListeJoueurs(state.value.partie.joueurs)
            Button(onClick = { goToGame() }) {
                Text(stringResource(id = R.string.start_game))
            }
        }
    }
}

@Composable
fun ListeJoueurs(joueurs: List<JoueurSimple>) {
    for (joueur in joueurs) {
        JoueurContainer(joueur)
    }
}

@Composable
fun JoueurContainer(joueur: JoueurSimple) {
    Row {
       Text("Pseudo: " + joueur.pseudo)
    }
}