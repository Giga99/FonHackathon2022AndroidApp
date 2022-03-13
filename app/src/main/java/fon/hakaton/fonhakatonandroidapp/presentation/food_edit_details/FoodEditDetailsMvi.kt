package fon.hakaton.fonhakatonandroidapp.presentation.food_edit_details

sealed class FoodEditDetailsIntent {
    object ErrorDialogDismissed : FoodEditDetailsIntent()
    object BackClicked : FoodEditDetailsIntent()
    data class FinishedBeefEdit(val beef: Int) : FoodEditDetailsIntent()
    data class FinishedOtherMeatEdit(val otherMeat: Int) : FoodEditDetailsIntent()
    data class FinishedAnimalProductsEdit(val animalProducts: Int) : FoodEditDetailsIntent()
    data class FinishedVegetablesEdit(val vegetables: Int) : FoodEditDetailsIntent()
    data class FinishedPastryEdit(val pastry: Int) : FoodEditDetailsIntent()
    object SaveInputClicked : FoodEditDetailsIntent()
}

sealed class FoodEditDetailsSideEffect {
    data class ShowMessage(val text: String) : FoodEditDetailsSideEffect()
    object InputFinished : FoodEditDetailsSideEffect()
}

data class FoodEditDetailsViewState(
    val animalProducts: Int = 0,
    val pastry: Int = 0,
    val vegetables: Int = 0,
    val beef: Int = 0,
    val otherMeat: Int = 0,
)
