package fon.hakaton.fonhakatonandroidapp.presentation.carbon_tracker

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fon.hakaton.fonhakatonandroidapp.common.AppViewModel
import fon.hakaton.fonhakatonandroidapp.common.Result
import fon.hakaton.fonhakatonandroidapp.data.remote.requests.UserRequest
import fon.hakaton.fonhakatonandroidapp.domain.repos.HomeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CarbonTrackerViewModel @Inject constructor(
    private val homeRepo: HomeRepo,
    savedStateHandle: SavedStateHandle
) : AppViewModel<CarbonTrackerViewState, CarbonTrackerIntent, CarbonTrackerSideEffect>(
    viewState = CarbonTrackerViewState()
) {

    fun getData(id: Int, username: String) {
        viewModelScope.launch(context = Dispatchers.IO) {
            val result = homeRepo.getOverview(UserRequest(id, username))
            if (result is Result.Success) {
                setState {
                    copy(
                        carbonOverviewModel = result.data!!
                    )
                }
            } else if (result is Result.Error) {
                Timber.d("RESULT: ${result.message}")
            }
        }
    }

    override suspend fun processIntent(intent: CarbonTrackerIntent) {
        when (intent) {
            is CarbonTrackerIntent.BackClicked -> {}
            is CarbonTrackerIntent.ErrorDialogDismissed -> {}
        }
    }
}
