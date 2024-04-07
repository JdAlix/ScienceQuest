package fr.iut.sciencequest.view.games.kahoot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.iut.sciencequest.R
import fr.iut.sciencequest.viewModels.KahootJoinViewModel

@Preview
@Composable
fun RejoindreKahootAvecCodeContainer(kahootJoinVM: KahootJoinViewModel = viewModel()) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(stringResource(id = R.string.join_game))
        KahootCodeInputContainer(kahootJoinVM)
    }
}

@Preview
@Composable
fun KahootCodeInputContainer(kahootJoinVM: KahootJoinViewModel = viewModel()) {
    val state = kahootJoinVM.uiState.collectAsState()
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextField(value = state.value.code,
            onValueChange = {kahootJoinVM.setCode(it)})
        Button(onClick = { /*TODO*/ }) {
            Text(text = stringResource(id = R.string.join))
        }
    }
}