package fon.hakaton.fonhakatonandroidapp.presentation.utilities_edit_details

sealed class UtilitiesEditDetailsIntent {
    object ErrorDialogDismissed : UtilitiesEditDetailsIntent()
    object BackClicked : UtilitiesEditDetailsIntent()
}

sealed class UtilitiesEditDetailsSideEffect {
    data class ShowMessage(val text: String) : UtilitiesEditDetailsSideEffect()
}

data class UtilitiesEditDetailsViewState(
    val numberOfResidents: Int = 4,
    val apartmentSize: Int = 60,
    val consumption: Long = 100,
)