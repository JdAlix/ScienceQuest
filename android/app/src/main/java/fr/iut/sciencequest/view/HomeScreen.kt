package fr.iut.sciencequest.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.iut.sciencequest.ViewModels.KahootViewModel

@Composable
fun HomeScreen(goToHome: () -> Unit,
               goToAccount: () -> Unit,
               goToPendu: () -> Unit,
               goToKahoot: () -> Unit) {
    Column (modifier = Modifier.fillMaxWidth()){
        TopBar(goToAccount, goToHome)
        MainContent(goToPendu, goToKahoot)
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen({},{},{}, {})
}

@Composable
fun MainContent(goToPendu: () -> Unit, goToKahoot: () -> Unit) {
    val context = LocalContext.current;
    val comingSoon = Toast.makeText(context, "Coming soon", Toast.LENGTH_SHORT);

    Column (modifier = Modifier.fillMaxWidth().padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(15.dp
    )){
        Button(onClick = goToPendu, Modifier.fillMaxWidth()) {
            Text(text = "Pendu", fontSize = 13.sp)
        }
        Button(onClick = goToKahoot, Modifier.fillMaxWidth()) {
            Text(text = "Kahoot", fontSize = 13.sp)
        }
        Button(onClick = { comingSoon.show() }, Modifier.fillMaxWidth()) {
            Text(text = "Qui est ce ?", fontSize = 13.sp)
        }
    }
}