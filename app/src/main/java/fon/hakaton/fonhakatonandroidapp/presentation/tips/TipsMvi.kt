package fon.hakaton.fonhakatonandroidapp.presentation.tips

import fon.hakaton.fonhakatonandroidapp.domain.models.TipModel

sealed class TipsIntent {
    object ErrorDialogDismissed : TipsIntent()
    object BackClicked : TipsIntent()
    object NextClicked : TipsIntent()
    object PreviousClicked : TipsIntent()
}

sealed class TipsSideEffect {
    data class ShowMessage(val text: String) : TipsSideEffect()
}

data class TipsViewState(
    val tips: List<TipModel> = listOf(
        TipModel(title = "Title1", description = "Description1"),
        TipModel(title = "Title2", description = "Description2"),
        TipModel(title = "Title3", description = "Description3"),
        TipModel(title = "Title4", description = "Description4"),
        TipModel(title = "Title5", description = "Description5"),
    ),
    val currentIndex: Int = 0,
)
