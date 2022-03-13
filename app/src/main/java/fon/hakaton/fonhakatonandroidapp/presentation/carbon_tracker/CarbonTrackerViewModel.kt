package fon.hakaton.fonhakatonandroidapp.presentation.carbon_tracker

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import fon.hakaton.fonhakatonandroidapp.common.AppViewModel
import javax.inject.Inject

@HiltViewModel
class CarbonTrackerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : AppViewModel<CarbonTrackerViewState, CarbonTrackerIntent, CarbonTrackerSideEffect>(
    viewState = CarbonTrackerViewState()
) {

    init {

    }

    override suspend fun processIntent(intent: CarbonTrackerIntent) {
        when (intent) {
            is CarbonTrackerIntent.BackClicked -> {}
            is CarbonTrackerIntent.ErrorDialogDismissed -> {}
        }
    }
}
