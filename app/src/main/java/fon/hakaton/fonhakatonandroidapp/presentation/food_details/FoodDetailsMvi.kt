package fon.hakaton.fonhakatonandroidapp.presentation.food_details

sealed class FoodDetailsIntent {
    object ErrorDialogDismissed : FoodDetailsIntent()
    object BackClicked : FoodDetailsIntent()
}

sealed class FoodDetailsSideEffect {
    data class ShowMessage(val text: String) : FoodDetailsSideEffect()
}

data class FoodDetailsViewState(
    val temp: Int = 0
)