package fr.iut.sciencequest.view.scientifiques

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.metier.Scientifique
import fr.iut.sciencequest.stub.getScientifiqueListeStub

@Preview
@Composable
fun scientifiqueListeContainer(scientifiques: List<Scientifique> = getScientifiqueListeStub().ToModel(),
                               innerPadding : PaddingValues = PaddingValues(Dp(0f))) {
    LazyColumn (
        modifier = Modifier.padding(innerPadding)
    ){
        items(scientifiques) {
            scientifiqueContainer(it)
        }
    }
}