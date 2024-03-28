package fr.iut.sciencequest.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.iut.sciencequest.R
import fr.iut.sciencequest.ViewModels.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel(),
                goToAccount: () -> Unit,
                goToHome: () -> Unit) {
    Scaffold(
        topBar = {
            TopBar(goToAccount, goToHome, stringResource(id = R.string.connection))
        },
    ) { innerPadding ->
        LoginContainer(viewModel, Modifier.padding(innerPadding))
    }
}

@Composable
fun LoginContainer(viewModel: LoginViewModel,
                   modifier: Modifier) {
    val state = viewModel.uiState.collectAsState()
    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //Text(text = stringResource(id = R.string.connection))
        TextField(value = state.value.pseudo,
            onValueChange = { viewModel.setPseudo(it) },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
        )
        TextField(value = state.value.mdp,
            onValueChange = { viewModel.setMdp(it) },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        )

        Button(onClick = { /*TODO*/ },
            modifier = Modifier.padding(20.dp),
            ) {
            Text(text = stringResource(id = R.string.connection))
        }

        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.no_account)),
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
                color = colorResource(id = R.color.purple_200)
            ),
            onClick = { }/*TODO*/ //registerPopup()
        )

    }
}

@Composable
fun registerClick(text: Int): () -> Unit {
    Toast.makeText(LocalContext.current, text, Toast.LENGTH_SHORT).show()
    return {}
}

@Composable
fun registerPopup(): (Int) -> Unit {
    AlertDialog(
        onDismissRequest = { },
        title = {
            Text(text = stringResource(id = R.string.no_account))
        },
        text = {
            Text(text = stringResource(id = R.string.no_account_details))
        },
        confirmButton = {
            Button(onClick = registerClick(R.string.coming_soon)) {
                Text(text = stringResource(id = R.string.inscription))
            }
        },
        dismissButton = {
            Button(onClick = { /*TODO*/ }) {
                Text(text = stringResource(id = R.string.continue_no_acc))
            }
        }
    )

    return {}
}

@Composable
@Preview
fun LoginScreenPreview(){
    LoginScreen(goToAccount = {}, goToHome = {})
}
