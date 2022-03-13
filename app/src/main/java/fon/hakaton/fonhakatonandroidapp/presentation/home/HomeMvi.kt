package fon.hakaton.fonhakatonandroidapp.presentation.home

sealed class HomeIntent {
    object ErrorDialogDismissed : HomeIntent()
    object BackClicked : HomeIntent()
    object OverviewTabClicked : HomeIntent()
    object TipsTabClicked : HomeIntent()
}

sealed class HomeSideEffect {
    data class ShowMessage(val text: String) : HomeSideEffect()
}

data class HomeViewState(
    val overviewSelected: Boolean = true,
)