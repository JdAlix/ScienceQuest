package fr.iut.sciencequest.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(goToHome: () -> Unit, goToAccount: () -> Unit) {
    TopBar(goToAccount, goToHome)
    MainContent()
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen({},{})
}

@Composable
fun MainContent() {

}