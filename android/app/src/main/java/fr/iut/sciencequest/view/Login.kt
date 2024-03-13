package fr.iut.sciencequest.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.iut.sciencequest.R

@Composable
fun LoginScreen(goToAccount: () -> Unit, goToHome: () -> Unit) {
    Scaffold(
        topBar = {
            TopBar(goToAccount, goToHome, stringResource(id = R.string.connection))
        },
    ) { innerPadding ->
        LoginContainer(innerPadding)
    }
}

@Composable
fun LoginContainer(innerPadding : PaddingValues) {
    Column(
        modifier = Modifier.padding(innerPadding)
    ) {
        val defaultPseudo = stringResource(id = R.string.Pseudo)
        var pseudo by remember {
             mutableStateOf(defaultPseudo)
        }
        val defaultMdp = stringResource(id = R.string.mdp)
        var mdp by remember {
            mutableStateOf(defaultMdp)
        }

        //Text(text = stringResource(id = R.string.connection))
        TextField(value = pseudo ,
            onValueChange = { pseudo = it },
            modifier = Modifier.padding(20.dp)
        )
        TextField(value = mdp,
            onValueChange = { mdp = it },
            modifier = Modifier.padding(20.dp)
        )

        Button(onClick = { /*TODO*/ },
            modifier = Modifier.padding(20.dp),
            ) {
            Text(text = stringResource(id = R.string.connection))
        }
    }
}

