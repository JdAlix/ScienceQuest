package fr.iut.sciencequest.view.games.kahoot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.iut.sciencequest.R
import fr.iut.sciencequest.view.TopBar
import fr.iut.sciencequest.viewModels.KahootJoinViewModel
import fr.iut.sciencequest.viewModels.KahootViewModel

@Composable
fun MenuKahoot(kahootViewModel: KahootViewModel = viewModel(factory = KahootViewModel.ApiFactory),
               kahootJoinVM: KahootJoinViewModel = viewModel(),
               goToAccount: () -> Unit,
               goToHome: () -> Unit,
               goToWaiting: (KahootViewModel) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopBar(goToAccount, goToHome, stringResource(id = R.string.kahoot))
        RejoindreKahootAvecCodeContainer()
        Button(onClick = { goToWaiting(kahootViewModel) }) {
            Text("Creer une partie")
        }
    }
}