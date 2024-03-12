package com.example.sciencequest.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.example.sciencequest.R

@Composable
fun HomeScreen(goToHome: () -> Unit, goToAccount: () -> Unit) {
    topBar(goToAccount, goToHome)
    mainContent()
}

@Composable
fun mainContent() {

}