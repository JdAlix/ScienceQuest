package fr.iut.sciencequest.ViewModels.UiStates

data class PenduUIState(
    val isActionGood: Boolean = false,
    val nbViesRestantes: Int = 10,
    val motATrouver: String = "Mot",
    val motATrou: String = "___"
)
