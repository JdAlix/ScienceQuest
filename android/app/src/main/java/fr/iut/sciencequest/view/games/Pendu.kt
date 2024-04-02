package fr.iut.sciencequest.view.games

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.iut.sciencequest.R
import fr.iut.sciencequest.viewModels.PenduViewModel
import fr.iut.sciencequest.view.TopBar

@Composable
fun PenduScreen(viewModel: PenduViewModel = viewModel(factory = PenduViewModel.ApiFactory),
                goToAccount: () -> Unit,
                goToHome: () -> Unit
) {
    val state = viewModel.uiState.collectAsState()
    val context = LocalContext.current;
    Column(modifier = Modifier.fillMaxWidth()) {
        TopBar(goToAccount, goToHome, stringResource(id = R.string.pendu))
        //Text(text = stringResource(id = R.string.pendu), modifier = Modifier.padding(top=10.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = state.value.motATrou, fontSize = 20.sp)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                TextField(value = "",
                    onValueChange = { onLetterEntered(it, viewModel, context, goToHome) },
                    modifier = Modifier.padding(20.dp),
                    enabled = !state.value.isWon && state.value.nbViesRestantes > 0,
                    placeholder = { Text(if(!state.value.isWon) "Entrez une lettre" else "Cliquez sur Nouvelle Partie") }
                )
                afficherVies(state.value.nbViesRestantes)
                Text("Lettres utilisées: " + state.value.lettresUtilises)
                if(state.value.isWon) {
                    Text(modifier = Modifier.padding(top=10.dp), text = "Vous avez gagné !")
                } else if (state.value.nbViesRestantes == 0) {
                    Text(modifier = Modifier.padding(top=10.dp), text = "Vous avez perdu :(")
                }
            }
            Button(onClick = { viewModel.InitPartie() }) {
                Text(text = stringResource(id = R.string.reset_game))
            }
        }
    }
}

fun onLetterEntered(entered: String,
                    vm: PenduViewModel,
                    context: Context,
                    goToHome: () -> Unit) {
    val state = vm.uiState
    if (entered.isNotEmpty()) {
        vm.PlayAction(entered[0])
        if ((!state.value.isWon) && (state.value.nbViesRestantes == 0)) {
            Toast.makeText(context,"Vous avez perdu :(",Toast.LENGTH_LONG).show()
        } else if (state.value.isWon) {
            Toast.makeText(context,"Vous avez gagné !",Toast.LENGTH_LONG).show()
        }
    }
}

@Preview
@Composable
fun afficherVies(nbVies : Int = 10) {
    Row {
       for (i in 1..nbVies) {
           Text("\u2665 ")
       }
    }
}

@Preview
@Composable
fun PenduPreview(){
    //PenduScreen(goToAccount = {}) {
//
    //}
}