package fr.iut.sciencequest.view.scientifiques

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import fr.iut.sciencequest.model.dto.extensions.ToModel
import fr.iut.sciencequest.model.metier.Scientifique
import fr.iut.sciencequest.stub.StubScientifique1

@Preview
@Composable
fun scientifiqueContainer(scientifique: Scientifique = StubScientifique1.ToModel()) {
    Row {
        Column {
            Text(scientifique.nom, modifier = Modifier.padding(Dp(3f)))
            Text(scientifique.prenom, modifier = Modifier.padding(Dp(3f)))
        }
        Text(scientifique.descriptif, modifier = Modifier.padding(Dp(3f)))
        Text(scientifique.sexe.toString(), modifier = Modifier.padding(Dp(3f)))
    }
}