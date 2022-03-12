package fon.hakaton.fonhakatonandroidapp.presentation.home

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import fon.hakaton.fonhakatonandroidapp.common.AppViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : AppViewModel<HomeViewState, HomeIntent, HomeSideEffect>(
    viewState = HomeViewState()
) {

    init {

    }

    override suspend fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.BackClicked -> {}
            is HomeIntent.ErrorDialogDismissed -> {}
        }
    }
}
