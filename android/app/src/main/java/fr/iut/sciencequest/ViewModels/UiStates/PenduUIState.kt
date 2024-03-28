package fr.iut.sciencequest.ViewModels.UiStates

data class PenduUIState(
    val isWon: Boolean = false,
    val isActionGood: Boolean = false,
    val nbViesRestantes: Int = 10,
    val motATrouver: String = "Mot",
    val motATrou: String = "___"
)
