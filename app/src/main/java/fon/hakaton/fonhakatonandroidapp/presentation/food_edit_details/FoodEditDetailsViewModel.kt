package fon.hakaton.fonhakatonandroidapp.presentation.food_edit_details

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import fon.hakaton.fonhakatonandroidapp.common.AppViewModel
import fon.hakaton.fonhakatonandroidapp.common.Destinations
import javax.inject.Inject

@HiltViewModel
class FoodEditDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : AppViewModel<FoodEditDetailsViewState, FoodEditDetailsIntent, FoodEditDetailsSideEffect>(
    viewState = FoodEditDetailsViewState()
) {

    init {
        val beef =
            savedStateHandle.get<String>(Destinations.FoodEditDetailsScreen.BEEF)?.toInt() ?: 0
        val otherMeat =
            savedStateHandle.get<String>(Destinations.FoodEditDetailsScreen.OTHER_MEAT)?.toInt()
                ?: 0
        val animalProducts =
            savedStateHandle.get<String>(Destinations.FoodEditDetailsScreen.ANIMAL_PRODUCTS)
                ?.toInt() ?: 0
        val vegetables =
            savedStateHandle.get<String>(Destinations.FoodEditDetailsScreen.VEGETABLES)?.toInt()
                ?: 0
        val pastry =
            savedStateHandle.get<String>(Destinations.FoodEditDetailsScreen.PASTRY)?.toInt() ?: 0

        setState {
            copy(
                beef = beef,
                otherMeat = otherMeat,
                animalProducts = animalProducts,
                vegetables = vegetables,
                pastry = pastry
            )
        }
    }

    override suspend fun processIntent(intent: FoodEditDetailsIntent) {
        when (intent) {
            is FoodEditDetailsIntent.BackClicked -> {}
            is FoodEditDetailsIntent.ErrorDialogDismissed -> {}
            is FoodEditDetailsIntent.FinishedBeefEdit -> {
                setState { copy(beef = intent.beef) }
            }
            is FoodEditDetailsIntent.FinishedOtherMeatEdit -> {
                setState { copy(beef = intent.otherMeat) }
            }
            is FoodEditDetailsIntent.FinishedAnimalProductsEdit -> {
                setState { copy(beef = intent.animalProducts) }
            }
            is FoodEditDetailsIntent.FinishedVegetablesEdit -> {
                setState { copy(beef = intent.vegetables) }
            }
            is FoodEditDetailsIntent.FinishedPastryEdit -> {
                setState { copy(beef = intent.pastry) }
            }
            is FoodEditDetailsIntent.SaveInputClicked -> {
                _sideEffects.tryEmit(FoodEditDetailsSideEffect.InputFinished)
            }
        }
    }
}
