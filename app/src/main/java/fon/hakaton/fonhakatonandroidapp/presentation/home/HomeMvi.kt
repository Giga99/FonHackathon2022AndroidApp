package fon.hakaton.fonhakatonandroidapp.presentation.home

sealed class HomeIntent {
    object ErrorDialogDismissed : HomeIntent()
    object BackClicked : HomeIntent()
}

sealed class HomeSideEffect {
    data class ShowMessage(val text: String) : HomeSideEffect()
}

data class HomeViewState(
    val temp: Int = 0
)