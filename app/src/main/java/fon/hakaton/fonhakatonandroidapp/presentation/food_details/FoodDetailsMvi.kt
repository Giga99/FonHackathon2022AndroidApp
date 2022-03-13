package fon.hakaton.fonhakatonandroidapp.presentation.food_details

import fon.hakaton.fonhakatonandroidapp.domain.models.FoodModel
import fon.hakaton.fonhakatonandroidapp.domain.models.TipModel

sealed class FoodDetailsIntent {
    object ErrorDialogDismissed : FoodDetailsIntent()
    object BackClicked : FoodDetailsIntent()
}

sealed class FoodDetailsSideEffect {
    data class ShowMessage(val text: String) : FoodDetailsSideEffect()
}

data class FoodDetailsViewState(
    val foodModel: FoodModel = FoodModel(
        12345,
        189,
        189,
        189,
        189,
        189,
        40.8f,
        "40%",
        36f,
        33.5f,
        TipModel(
            "Standby power draw",
            "Despite being “switched off” almost all electrical devices continue operate in a standby mode and continue using electricity even when they’re not in active use. This standby power draw accounts for about 10% of an average household's annual electricity use. Unplug your appliances or turn them off at the socket to reduce your monthly carbon footprint by x%."
        )
    )
)