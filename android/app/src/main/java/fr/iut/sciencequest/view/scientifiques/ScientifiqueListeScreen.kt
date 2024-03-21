package fr.iut.sciencequest.view.scientifiques

import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.iut.sciencequest.R
import fr.iut.sciencequest.ViewModels.ScientifiquesDecouvertsVM
import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.metier.Scientifique
import fr.iut.sciencequest.stub.getScientifiqueListeStub
import fr.iut.sciencequest.view.TopBar

@Composable
fun scientifiqueListeScreen(viewModel: ScientifiquesDecouvertsVM = viewModel(),
                            goToAccount: () -> Unit,
                            goToHome: () -> Unit) {
    Log.d("Vue","je suis la")
    Scaffold(
        topBar = {
            TopBar(goToAccount, goToHome, stringResource(id = R.string.sc_decouverts))
        },
    ) { innerPadding ->
        scientifiqueListeContainer(viewModel.getScientifiques(), innerPadding)
    }
}