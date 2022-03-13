package fon.hakaton.fonhakatonandroidapp.presentation.utilities_edit_details

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import fon.hakaton.fonhakatonandroidapp.common.AppViewModel
import javax.inject.Inject

@HiltViewModel
class UtilitiesEditDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : AppViewModel<UtilitiesEditDetailsViewState, UtilitiesEditDetailsIntent, UtilitiesEditDetailsSideEffect>(
    viewState = UtilitiesEditDetailsViewState()
) {

    init {

    }

    override suspend fun processIntent(intent: UtilitiesEditDetailsIntent) {
        when (intent) {
            is UtilitiesEditDetailsIntent.BackClicked -> {}
            is UtilitiesEditDetailsIntent.ErrorDialogDismissed -> {}
            is UtilitiesEditDetailsIntent.NumberOfResidentsInputChanged -> {
                setState { copy(numberOfResidents = intent.numberOfResidents.toInt()) }
            }
            is UtilitiesEditDetailsIntent.ApartmentSizeInputChanged -> {
                setState { copy(apartmentSize = intent.apartmentSize.toInt()) }
            }
            is UtilitiesEditDetailsIntent.ConsumptionInputChanged -> {
                setState { copy(consumption = intent.consumption.toLong()) }
            }
            is UtilitiesEditDetailsIntent.RenewableEnergyInputChanged -> {
                setState { copy(renewableEnergyPercent = intent.renewableEnergyPercent.toInt()) }
            }
            is UtilitiesEditDetailsIntent.SaveInputClicked -> {

            }
        }
    }
}
