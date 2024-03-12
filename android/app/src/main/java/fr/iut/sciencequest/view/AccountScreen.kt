package fr.iut.sciencequest.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import fr.iut.sciencequest.R

@Composable
fun AccountScreen(goToAccount: () -> Unit, goToHome: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopBar(goToAccount, goToHome)
        Text(text = stringResource(id = R.string.Compte))
    }
}

@Preview
@Composable
fun AccountScreenPreview(){
    AccountScreen(goToAccount = {}) {
        
    }
}