package fon.hakaton.fonhakatonandroidapp.presentation.utilities_details

import fon.hakaton.fonhakatonandroidapp.domain.models.UtilityModel

sealed class UtilitiesDetailsIntent {
    object ErrorDialogDismissed : UtilitiesDetailsIntent()
    object BackClicked : UtilitiesDetailsIntent()
}

sealed class UtilitiesDetailsSideEffect {
    data class ShowMessage(val text: String) : UtilitiesDetailsSideEffect()
}

data class UtilitiesDetailsViewState(
    val utility: UtilityModel = UtilityModel()
)