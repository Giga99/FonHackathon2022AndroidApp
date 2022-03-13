package fon.hakaton.fonhakatonandroidapp.presentation.utilities_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fon.hakaton.fonhakatonandroidapp.common.AppViewModel
import fon.hakaton.fonhakatonandroidapp.common.Destinations
import fon.hakaton.fonhakatonandroidapp.common.Result
import fon.hakaton.fonhakatonandroidapp.data.remote.requests.CarbonUser
import fon.hakaton.fonhakatonandroidapp.data.remote.requests.UserRequest2
import fon.hakaton.fonhakatonandroidapp.domain.models.TipModel
import fon.hakaton.fonhakatonandroidapp.domain.models.UtilityModel
import fon.hakaton.fonhakatonandroidapp.domain.repos.UtilitiesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UtilitiesDetailsViewModel @Inject constructor(
    private val utilitiesRepo: UtilitiesRepo,
    savedStateHandle: SavedStateHandle
) : AppViewModel<UtilitiesDetailsViewState, UtilitiesDetailsIntent, UtilitiesDetailsSideEffect>(
    viewState = UtilitiesDetailsViewState()
) {

    init {
        val id2 =
            savedStateHandle.get<String>(Destinations.UtilitiesDetailsScreen.ID)?.toLong() ?: 0
        val username2 =
            savedStateHandle.get<String>(Destinations.UtilitiesDetailsScreen.USERNAME) ?: ""
        val name =
            savedStateHandle.get<String>(Destinations.UtilitiesDetailsScreen.NAME) ?: ""
        val isElectricity =
            savedStateHandle.get<String>(Destinations.UtilitiesDetailsScreen.IS_ELECTRICITY)
                .toBoolean()

        viewModelScope.launch(context = Dispatchers.IO) {
            val response = utilitiesRepo.getUtility(
                UserRequest2(carbonUser = CarbonUser(id = id2, username = username2)),
                isElectricity
            )
            if (response is Result.Success) {
                setState {
                    copy(
                        id = id2,
                        username = username2,
                        name = name,
                        utility = response.data ?: UtilityModel(),
                        tip = if(response.data?.isElectricity == false) TipModel(
                            title = "Beware of toilet leakage",
                            description = "Check your toilet tank for leaks. Put a few drops of food coloring in your toilet tank. If, without flushing, the coloring begins to appear in the bowl, you have a leak that may be wasting more than 100 gallons of water a day."
                        ) else tip
                    )
                }
            } else if (response is Result.Error) {
                Timber.d("RESULT: ${response.message}")
            }
        }
    }

    override suspend fun processIntent(intent: UtilitiesDetailsIntent) {
        when (intent) {
            is UtilitiesDetailsIntent.BackClicked -> {}
            is UtilitiesDetailsIntent.ErrorDialogDismissed -> {}
        }
    }
}
