package fr.iut.sciencequest.ViewModels.UiStates

data class PenduUIState(
    val isActionGood: Boolean,
    val nbViesRestantes: Int,
    val motATrouver: String,
    val motATrou: String,
    val lettreJoue: Char
)
