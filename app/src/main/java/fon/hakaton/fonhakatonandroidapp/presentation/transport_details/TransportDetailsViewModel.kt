package fon.hakaton.fonhakatonandroidapp.presentation.transport_details

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import fon.hakaton.fonhakatonandroidapp.common.AppViewModel
import javax.inject.Inject

@HiltViewModel
class TransportDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : AppViewModel<TransportDetailsViewState, TransportDetailsIntent, TransportDetailsSideEffect>(
    viewState = TransportDetailsViewState()
) {

    init {

    }

    override suspend fun processIntent(intent: TransportDetailsIntent) {
        when (intent) {
            is TransportDetailsIntent.BackClicked -> {}
            is TransportDetailsIntent.ErrorDialogDismissed -> {}
        }
    }
}
