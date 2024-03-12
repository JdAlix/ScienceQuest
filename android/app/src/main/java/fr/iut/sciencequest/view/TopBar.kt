package fr.iut.sciencequest.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import fr.iut.sciencequest.R

@Composable
fun TopBar(goToAccount: () -> Unit, goToHome: () -> Unit, modifier: Modifier? = Modifier.fillMaxWidth()) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        FilledIconButton(onClick = { goToHome() }) {
            Image(painter = painterResource(id = R.drawable.menu), contentDescription = "Menu")
        }
        Text(text = stringResource(id = R.string.app_name))
        FilledIconButton(onClick = { goToAccount() }) {
            Image(painter = painterResource(id = R.drawable.account_circle_outline), contentDescription = "Account")
        }
    }
}

@Preview
@Composable
fun TopBarPreview(){
    TopBar(goToAccount = {}, goToHome = {})
}