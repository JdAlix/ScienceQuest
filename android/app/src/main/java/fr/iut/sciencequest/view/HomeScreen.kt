package fr.iut.sciencequest.view

import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(goToHome: () -> Unit, goToAccount: () -> Unit) {
    topBar(goToAccount, goToHome)
    mainContent()
}

@Composable
fun mainContent() {

}