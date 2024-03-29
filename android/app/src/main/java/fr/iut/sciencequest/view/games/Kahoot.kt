package fr.iut.sciencequest.view.games

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.iut.sciencequest.R
import fr.iut.sciencequest.ViewModels.KahootViewModel
import fr.iut.sciencequest.model.dto.question.QuestionWithSimpleResponseDTO
import fr.iut.sciencequest.model.dto.reponse.ReponseSimpleDTO
import fr.iut.sciencequest.stub.StubQuestionWithReponses
import fr.iut.sciencequest.view.TopBar
import java.util.Timer

@Composable
fun KahootScreen(viewModel: KahootViewModel = viewModel(),
                 goToAccount: () -> Unit,
                 goToHome: () -> Unit) {
    val state = viewModel.uiState.collectAsState()
    Column(modifier = Modifier.fillMaxWidth()) {
        TopBar(goToAccount, goToHome, stringResource(id = R.string.kahoot))
        KahootPlayer(state.value.question) {
            viewModel.ajouterPoints(it)
        }
    }
}

@Preview
@Composable
fun KahootScreenPreview(){
    KahootScreen(goToAccount = {}, goToHome = {})
}


@Preview
@Composable
fun KahootPlayerPreview(){
    val i = 0
    KahootPlayer(question = StubQuestionWithReponses) {}
}


@Composable
fun KahootPlayer(question: QuestionWithSimpleResponseDTO,
                 sendReponse: (Long) -> Unit){
    val context = LocalContext.current;
    val currTime = System.currentTimeMillis()
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        KahootQuestion(question = question.question)
        KahootReponses(reponses = question.reponses) {
            sendReponse(currTime - System.currentTimeMillis())
            Toast.makeText(context, it.reponse, Toast.LENGTH_SHORT).show()
        }
    }
}


@Composable
fun KahootReponses(reponses : List<ReponseSimpleDTO>, action: (ReponseSimpleDTO)->Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(2),
                     contentPadding = PaddingValues(12.dp),
                     verticalArrangement = Arrangement.spacedBy(10.dp),
                     horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        reponses.forEach {
            item() {
                Button(onClick = {action(it)}){
                    Text(it.reponse)
                }
            }
        }
    }
}

@Composable
fun KahootQuestion(question: String){
    Text(question, textAlign = TextAlign.Center)
}