package fon.hakaton.fonhakatonandroidapp.presentation.utilities_details

import fon.hakaton.fonhakatonandroidapp.domain.models.TipModel
import fon.hakaton.fonhakatonandroidapp.domain.models.UtilityModel

sealed class UtilitiesDetailsIntent {
    object ErrorDialogDismissed : UtilitiesDetailsIntent()
    object BackClicked : UtilitiesDetailsIntent()
}

sealed class UtilitiesDetailsSideEffect {
    data class ShowMessage(val text: String) : UtilitiesDetailsSideEffect()
}

data class UtilitiesDetailsViewState(
    val id: Long = 0,
    val username: String = "",
    val name: String = "",
    val utility: UtilityModel = UtilityModel(),
    val tip: TipModel = TipModel(
        "Standby power draw",
        "Despite being “switched off” almost all electrical devices continue operate in a standby mode and continue using electricity even when they’re not in active use. This standby power draw accounts for about 10% of an average household's annual electricity use. Unplug your appliances or turn them off at the socket to reduce your monthly carbon footprint by x%."
    )
)