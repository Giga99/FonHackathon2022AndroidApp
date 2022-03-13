package fon.hakaton.fonhakatonandroidapp.presentation.utilities_edit_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fon.hakaton.fonhakatonandroidapp.common.AppViewModel
import fon.hakaton.fonhakatonandroidapp.common.Destinations
import fon.hakaton.fonhakatonandroidapp.common.Result
import fon.hakaton.fonhakatonandroidapp.data.remote.requests.CarbonUser
import fon.hakaton.fonhakatonandroidapp.data.remote.responses.UtilityResponse
import fon.hakaton.fonhakatonandroidapp.domain.repos.UtilitiesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UtilitiesEditDetailsViewModel @Inject constructor(
    private val utilitiesRepo: UtilitiesRepo,
    savedStateHandle: SavedStateHandle
) : AppViewModel<UtilitiesEditDetailsViewState, UtilitiesEditDetailsIntent, UtilitiesEditDetailsSideEffect>(
    viewState = UtilitiesEditDetailsViewState()
) {

    init {
        val id =
            savedStateHandle.get<String>(Destinations.UtilitiesEditDetailsScreen.ID)?.toLong() ?: 0
        val username =
            savedStateHandle.get<String>(Destinations.UtilitiesEditDetailsScreen.USERNAME) ?: ""
        val name =
            savedStateHandle.get<String>(Destinations.UtilitiesEditDetailsScreen.NAME) ?: ""
        val consumption2 =
            savedStateHandle.get<String>(Destinations.UtilitiesEditDetailsScreen.CONSUMPTION)
                ?.toLong() ?: 0
        val renewableEnergy2 =
            savedStateHandle.get<String>(Destinations.UtilitiesEditDetailsScreen.RENEWABLE_ENERGY)
                ?.toInt() ?: 0

        setState {
            copy(
                id = id,
                username = username,
                name = name,
                consumption = consumption2,
                renewableEnergyPercent = renewableEnergy2,
                numberOfResidents = 4,
                apartmentSize = 40,
            )
        }
    }

    override suspend fun processIntent(intent: UtilitiesEditDetailsIntent) {
        when (intent) {
            is UtilitiesEditDetailsIntent.BackClicked -> {}
            is UtilitiesEditDetailsIntent.ErrorDialogDismissed -> {}
            is UtilitiesEditDetailsIntent.NumberOfResidentsInputChanged -> {
                setState { copy(numberOfResidents = intent.numberOfResidents.toIntOrNull() ?: 0) }
            }
            is UtilitiesEditDetailsIntent.ApartmentSizeInputChanged -> {
                setState { copy(apartmentSize = intent.apartmentSize.toIntOrNull() ?: 0) }
            }
            is UtilitiesEditDetailsIntent.ConsumptionInputChanged -> {
                setState { copy(consumption = intent.consumption.toLongOrNull() ?: 0) }
            }
            is UtilitiesEditDetailsIntent.RenewableEnergyInputChanged -> {
                setState {
                    copy(
                        renewableEnergyPercent = intent.renewableEnergyPercent.toIntOrNull() ?: 0
                    )
                }
            }
            is UtilitiesEditDetailsIntent.SaveInputClicked -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    val response = utilitiesRepo.saveUtility(
                        UtilityResponse(
                            carbonUser = CarbonUser(getState().id, getState().username),
                            amount = getState().consumption,
                            residents = getState().numberOfResidents.toLong(),
                            apartment_size = getState().apartmentSize.toLong(),
                        ),
                        isElectricity = getState().name == "Electricity"
                    )
                    if (response is Result.Success) {
                        _sideEffects.tryEmit(UtilitiesEditDetailsSideEffect.InputFinished)
                    } else if (response is Result.Error) {
                        Timber.d("RESULT: ${response.message}")
                    }
                }
            }
        }
    }
}
