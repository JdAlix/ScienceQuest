package fr.iut.sciencequest.ViewModels.UiStates

data class PenduUIState(
    val isActionGood: Boolean = false,
    val nbViesRestantes: Int = 10,
    val motATrouver: String = "",
    val motATrou: String = ""
)
