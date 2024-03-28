package fr.iut.sciencequest.view.games

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.iut.sciencequest.R
import fr.iut.sciencequest.ViewModels.PenduViewModel
import fr.iut.sciencequest.view.TopBar

@Composable
fun PenduScreen(viewModel: PenduViewModel = viewModel(),
                context: Context,
                goToAccount: () -> Unit,
                goToHome: () -> Unit) {
    val state = viewModel.uiState.collectAsState()
    Column(modifier = Modifier.fillMaxWidth()) {
        TopBar(goToAccount, goToHome, stringResource(id = R.string.pendu))
        //Text(text = stringResource(id = R.string.pendu), modifier = Modifier.padding(top=10.dp))
        Column(modifier = Modifier.align(Alignment.CenterHorizontally))
        {
            Text(text = state.value.motATrou)
            TextField(value = "",
                onValueChange = {
                    if (it.isNotEmpty()) {
                        viewModel.PlayAction(it[0])
                        if ((!state.value.isWon) && (state.value.nbViesRestantes == 0)) {
                            goToHome()
                        } else {
                            Toast.makeText(context,"Vous avez gagné !",Toast.LENGTH_LONG).show()
                        }
                    }},
                modifier = Modifier.padding(20.dp))
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