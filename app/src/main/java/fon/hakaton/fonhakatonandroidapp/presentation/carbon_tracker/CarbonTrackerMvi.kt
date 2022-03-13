package fon.hakaton.fonhakatonandroidapp.presentation.carbon_tracker

sealed class CarbonTrackerIntent {
    object ErrorDialogDismissed : CarbonTrackerIntent()
    object BackClicked : CarbonTrackerIntent()
}

sealed class CarbonTrackerSideEffect {
    data class ShowMessage(val text: String) : CarbonTrackerSideEffect()
}

data class CarbonTrackerViewState(
    val transport: Float = 0.32f,
    val water: Float = 0.15f,
    val food: Float = 0.15f,
    val electricity: Float = 0.15f,
)