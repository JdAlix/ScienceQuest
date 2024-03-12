package fr.iut.sciencequest.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(goToHome: () -> Unit, goToAccount: () -> Unit) {
    Column (modifier = Modifier.fillMaxWidth()){
        TopBar(goToAccount, goToHome)
        MainContent()
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen({},{})
}

@Composable
fun MainContent() {

}