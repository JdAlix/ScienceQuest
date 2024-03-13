package fr.iut.sciencequest.view.games

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import fr.iut.sciencequest.R
import fr.iut.sciencequest.view.TopBar

@Composable
fun PenduScreen(goToAccount: () -> Unit, goToHome: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopBar(goToAccount, goToHome, stringResource(id = R.string.pendu))
        //Text(text = stringResource(id = R.string.pendu), modifier = Modifier.padding(top=10.dp))
    }
}

@Preview
@Composable
fun PenduPreview(){
    PenduScreen(goToAccount = {}) {

    }
}