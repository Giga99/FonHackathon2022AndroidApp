package fon.hakaton.fonhakatonandroidapp.presentation.food_details

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import fon.hakaton.fonhakatonandroidapp.common.AppViewModel
import javax.inject.Inject

@HiltViewModel
class FoodDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : AppViewModel<FoodDetailsViewState, FoodDetailsIntent, FoodDetailsSideEffect>(
    viewState = FoodDetailsViewState()
) {

    init {

    }

    override suspend fun processIntent(intent: FoodDetailsIntent) {
        when (intent) {
            is FoodDetailsIntent.BackClicked -> {}
            is FoodDetailsIntent.ErrorDialogDismissed -> {}
        }
    }
}
