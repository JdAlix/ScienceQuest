package fr.iut.sciencequest.view.scientifiques

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.iut.sciencequest.R
import fr.iut.sciencequest.viewModels.ScientifiquesDecouvertsVM
import fr.iut.sciencequest.view.TopBar

@Composable
fun scientifiqueListeScreen(viewModel: ScientifiquesDecouvertsVM = viewModel(factory = ScientifiquesDecouvertsVM.ApiFactory),
                            goToAccount: () -> Unit,
                            goToHome: () -> Unit) {
    val liste by viewModel.listeScientifique.collectAsState()
    Scaffold(
        topBar = {
            TopBar(goToAccount, goToHome, stringResource(id = R.string.sc_decouverts))
        },
    ) { innerPadding ->
        LaunchedEffect(key1 = Unit) {
            viewModel.getScientifiques(1)
        }
        scientifiqueListeContainer(liste.scientifiques, innerPadding)
    }
}