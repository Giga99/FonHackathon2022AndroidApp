package fon.hakaton.fonhakatonandroidapp.presentation.carbon_tracker

import fon.hakaton.fonhakatonandroidapp.domain.models.CarbonOverviewModel

sealed class CarbonTrackerIntent {
    object ErrorDialogDismissed : CarbonTrackerIntent()
    object BackClicked : CarbonTrackerIntent()
}

sealed class CarbonTrackerSideEffect {
    data class ShowMessage(val text: String) : CarbonTrackerSideEffect()
}

data class CarbonTrackerViewState(
    val carbonOverviewModel: CarbonOverviewModel = CarbonOverviewModel()
)