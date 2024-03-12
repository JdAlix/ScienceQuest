package fr.iut.sciencequest.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp

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
    Column (modifier= Modifier.fillMaxWidth().padding(Dp(30f)), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(
        Dp(15f)
    )){
        Button(onClick = { /*TODO*/ }, Modifier.fillMaxWidth()) {
            Text(text = "Pendu", fontSize = 13.sp)
        }
        Button(onClick = { /*TODO*/ }, Modifier.fillMaxWidth()) {
            Text(text = "Kahoot", fontSize = 13.sp)
        }
        Button(onClick = { /*TODO*/ }, Modifier.fillMaxWidth()) {
            Text(text = "Qui est ce ?", fontSize = 13.sp)
        }
    }
}