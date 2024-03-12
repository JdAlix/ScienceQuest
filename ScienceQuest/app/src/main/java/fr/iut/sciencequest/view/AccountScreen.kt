package fr.iut.sciencequest.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AccountScreen(goToAccount: () -> Unit, goToHome: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        topBar(goToAccount, goToHome)
        Text(text = "Account")
    }
}