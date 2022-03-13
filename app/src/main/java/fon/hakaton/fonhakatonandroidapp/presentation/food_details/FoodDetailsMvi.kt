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
            "General Advice",
            "Data shows that food production is responsible for 25% of the world’s greenhouse gas emissions. Surprisingly, food transport is a small contributor accounting for less than 10% of these emissions. Eating less meat, specifically less beef will reduce your carbon footprint significantly more than buying locally produced food."
        )
    )
)