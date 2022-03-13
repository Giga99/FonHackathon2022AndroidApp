package fon.hakaton.fonhakatonandroidapp.presentation.utilities_details

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import fon.hakaton.fonhakatonandroidapp.common.AppViewModel
import fon.hakaton.fonhakatonandroidapp.common.Destinations
import fon.hakaton.fonhakatonandroidapp.domain.models.TipModel
import fon.hakaton.fonhakatonandroidapp.domain.models.UtilityModel
import javax.inject.Inject

@HiltViewModel
class UtilitiesDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : AppViewModel<UtilitiesDetailsViewState, UtilitiesDetailsIntent, UtilitiesDetailsSideEffect>(
    viewState = UtilitiesDetailsViewState()
) {

    init {
        val isElectricity =
            savedStateHandle.get<String>(Destinations.UtilitiesDetailsScreen.IS_ELECTRICITY).toBoolean()
        setState {
            copy(
                utility = if (isElectricity) {
                    UtilityModel(
                        name = "Electricity",
                        betterThanPercent = "40%",
                        lastMonth = 891,
                        averageConsumption = 936,
                        lastFiveMonths = listOf(
                            "Nov" to 930,
                            "Dec" to 960,
                            "Jan" to 990,
                            "Feb" to 930,
                            "Mar" to 891
                        ),
                        tip = TipModel(
                            "Standby power draw",
                            "Despite being “switched off” almost all electrical devices continue operate in a standby mode and continue using electricity even when they’re not in active use. This standby power draw accounts for about 10% of an average household's annual electricity use. Unplug your appliances or turn them off at the socket to reduce your monthly carbon footprint by x%."
                        ),
                        isElectricity = isElectricity,
                    )
                } else {
                    UtilityModel(
                        name = "Water",
                        betterThanPercent = "20%",
                        lastMonth = 1400,
                        averageConsumption = 1200,
                        lastFiveMonths = listOf(
                            "Nov" to 1400,
                            "Dec" to 1400,
                            "Jan" to 1000,
                            "Feb" to 1000,
                            "Mar" to 1200
                        ),
                        tip = TipModel(
                            "Standby power draw",
                            "Despite being “switched off” almost all electrical devices continue operate in a standby mode and continue using electricity even when they’re not in active use. This standby power draw accounts for about 10% of an average household's annual electricity use. Unplug your appliances or turn them off at the socket to reduce your monthly carbon footprint by x%."
                        ),
                        isElectricity = isElectricity,
                    )
                }
            )
        }
    }

    override suspend fun processIntent(intent: UtilitiesDetailsIntent) {
        when (intent) {
            is UtilitiesDetailsIntent.BackClicked -> {}
            is UtilitiesDetailsIntent.ErrorDialogDismissed -> {}
        }
    }
}
