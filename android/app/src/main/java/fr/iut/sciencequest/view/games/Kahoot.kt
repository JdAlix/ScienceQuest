package fr.iut.sciencequest.view.games

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import fr.iut.sciencequest.R
import fr.iut.sciencequest.model.dto.question.QuestionWithSimpleResponseDTO
import fr.iut.sciencequest.model.dto.reponse.ReponseSimpleDTO
import fr.iut.sciencequest.stub.StubQuestionWithReponses
import fr.iut.sciencequest.view.TopBar

@Composable
fun KahootScreen(goToAccount: () -> Unit, goToHome: () -> Unit, question: QuestionWithSimpleResponseDTO) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopBar(goToAccount, goToHome, stringResource(id = R.string.kahoot))
        KahootPlayer(question)
    }
}

@Preview
@Composable
fun KahootScreenPreview(){
    KahootScreen(goToAccount = {}, goToHome = {}, StubQuestionWithReponses)
}


@Preview
@Composable
fun KahootPlayerPreview(){
    KahootPlayer(question = StubQuestionWithReponses)
}


@Composable
fun KahootPlayer(question: QuestionWithSimpleResponseDTO){
    Column {
        KahootQuestion(question = question.question)
        KahootReponses(reponses = question.reponses)
    }
}


@Composable
fun KahootReponses(reponses : List<ReponseSimpleDTO>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        reponses.forEach {
            item() {
                Text(it.reponse)
            }
        }
    }
}

@Composable
fun KahootQuestion(question: String){
    Text(question)
}