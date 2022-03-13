package fon.hakaton.fonhakatonandroidapp.presentation.home

sealed class HomeIntent {
    object ErrorDialogDismissed : HomeIntent()
    object BackClicked : HomeIntent()
}

sealed class HomeSideEffect {
    data class ShowMessage(val text: String) : HomeSideEffect()
}

data class HomeViewState(
    val transport: Float = 0.32f,
    val water: Float = 0.15f,
    val food: Float = 0.15f,
    val electricity: Float = 0.15f,
)