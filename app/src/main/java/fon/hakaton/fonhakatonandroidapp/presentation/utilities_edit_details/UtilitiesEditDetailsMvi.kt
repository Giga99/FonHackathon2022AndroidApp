package fon.hakaton.fonhakatonandroidapp.presentation.utilities_edit_details

sealed class UtilitiesEditDetailsIntent {
    object ErrorDialogDismissed : UtilitiesEditDetailsIntent()
    object BackClicked : UtilitiesEditDetailsIntent()
    data class NumberOfResidentsInputChanged(val numberOfResidents: String) : UtilitiesEditDetailsIntent()
    data class ApartmentSizeInputChanged(val apartmentSize: String) : UtilitiesEditDetailsIntent()
    data class ConsumptionInputChanged(val consumption: String) : UtilitiesEditDetailsIntent()
    data class RenewableEnergyInputChanged(val renewableEnergyPercent: String) : UtilitiesEditDetailsIntent()
    object SaveInputClicked : UtilitiesEditDetailsIntent()
}

sealed class UtilitiesEditDetailsSideEffect {
    data class ShowMessage(val text: String) : UtilitiesEditDetailsSideEffect()
    object InputFinished : UtilitiesEditDetailsSideEffect()
}

data class UtilitiesEditDetailsViewState(
    val id: Long = 0,
    val username: String = "",
    val name: String = "Electricity",
    val numberOfResidents: Int = 4,
    val apartmentSize: Int = 60,
    val consumption: Long = 100,
    val renewableEnergyPercent: Int? = 30,
)