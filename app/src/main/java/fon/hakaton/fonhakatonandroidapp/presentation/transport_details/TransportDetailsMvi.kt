package fon.hakaton.fonhakatonandroidapp.presentation.transport_details

import fon.hakaton.fonhakatonandroidapp.domain.models.TransportModel

sealed class TransportDetailsIntent {
    object ErrorDialogDismissed : TransportDetailsIntent()
    object BackClicked : TransportDetailsIntent()
}

sealed class TransportDetailsSideEffect {
    data class ShowMessage(val text: String) : TransportDetailsSideEffect()
}

data class TransportDetailsViewState(
    val transport: TransportModel = TransportModel(
        12345,
        112.8f,
        "40%",
        36f,
        33.5f,
        5,
        15,
        35,
        20,
        100,
    )
)