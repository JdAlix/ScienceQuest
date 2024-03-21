package fr.iut.sciencequest.view.scientifiques

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import fr.iut.sciencequest.R
import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.metier.Scientifique
import fr.iut.sciencequest.stub.getScientifiqueListeStub
import fr.iut.sciencequest.view.TopBar

@Composable
fun scientifiqueListeScreen(scientifiques: List<Scientifique> = getScientifiqueListeStub().ToModel(),
                            goToAccount: () -> Unit,
                            goToHome: () -> Unit) {
    Scaffold(
        topBar = {
            TopBar(goToAccount, goToHome, stringResource(id = R.string.connection))
        },
    ) { innerPadding ->
        scientifiqueListeContainer(scientifiques, innerPadding)
    }
}