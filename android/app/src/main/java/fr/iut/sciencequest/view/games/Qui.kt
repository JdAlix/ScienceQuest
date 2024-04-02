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
import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.dto.question.QuestionWithSimpleResponseDTO
import fr.iut.sciencequest.model.dto.reponse.ReponseSimpleDTO
import fr.iut.sciencequest.model.metier.question.QuestionWithSimpleReponse
import fr.iut.sciencequest.model.metier.reponse.ReponseSimple
import fr.iut.sciencequest.stub.StubQuestionWithReponses
import fr.iut.sciencequest.view.TopBar
import fr.iut.sciencequest.viewModels.KahootViewModel
import java.util.Timer

@Composable
fun QuiScreen(viewModel: KahootViewModel = viewModel(),
                 goToAccount: () -> Unit,
                 goToHome: () -> Unit) {
    val state = viewModel.uiState.collectAsState()
    Column(modifier = Modifier.fillMaxWidth()) {
        TopBar(goToAccount, goToHome, stringResource(id = R.string.kahoot))
        QuiPlayer(state.value.question) {
            viewModel.ajouterPoints(it)
        }
    }
}

@Preview
@Composable
fun QuiScreenPreview(){
    QuiScreen(goToAccount = {}, goToHome = {})
}


@Preview
@Composable
fun QuiPlayerPreview(){
    val i = 0
    QuiPlayer(question = StubQuestionWithReponses.ToModel()) {}
}


@Composable
fun QuiPlayer(question: QuestionWithSimpleReponse,
                 sendReponse: (Long) -> Unit){
    val context = LocalContext.current;
    val currTime = System.currentTimeMillis()
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        QuiQuestion(question = question.question)
        QuiReponses(reponses = question.reponses) {
            sendReponse(currTime - System.currentTimeMillis())
            Toast.makeText(context, it.reponse, Toast.LENGTH_SHORT).show()
        }
    }
}


@Composable
fun QuiReponses(reponses : List<ReponseSimple>, action: (ReponseSimple)->Unit) {
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
fun QuiQuestion(question: String){
    Text(question, textAlign = TextAlign.Center)
}