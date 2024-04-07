package fr.iut.sciencequest.view.games.kahoot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.iut.sciencequest.R
import fr.iut.sciencequest.view.TopBar
import fr.iut.sciencequest.viewModels.KahootJoinViewModel
import fr.iut.sciencequest.viewModels.KahootViewModel

@Composable
fun ResultatKahoot(goToAccount: () -> Unit,
                   goToHome: () -> Unit,
                   kahootViewModel: KahootViewModel) {
    val state = kahootViewModel.uiState.collectAsState()
    Column(modifier = Modifier.fillMaxWidth()) {
        TopBar(goToAccount, goToHome, stringResource(id = R.string.kahoot))
        Column(modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Vous avez " + state.value.nbPoints + "points")
        }
    }
}