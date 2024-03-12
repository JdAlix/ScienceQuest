package fr.iut.sciencequest.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(goToHome: () -> Unit, goToAccount: () -> Unit) {
    topBar(goToAccount, goToHome)
    mainContent()
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen({},{})
}

@Composable
fun mainContent() {

}