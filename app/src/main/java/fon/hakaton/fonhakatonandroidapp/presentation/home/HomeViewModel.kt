package fon.hakaton.fonhakatonandroidapp.presentation.home

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import fon.hakaton.fonhakatonandroidapp.common.AppViewModel
import fon.hakaton.fonhakatonandroidapp.common.Destinations
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : AppViewModel<HomeViewState, HomeIntent, HomeSideEffect>(
    viewState = HomeViewState()
) {

    init {
        val id = savedStateHandle.get<String>(Destinations.HomeScreen.ID)?.toInt() ?: 0
        val username = savedStateHandle.get<String>(Destinations.HomeScreen.USERNAME) ?: ""
        val name = savedStateHandle.get<String>(Destinations.HomeScreen.NAME) ?: ""
        setState {
            copy(
                id = id,
                username = username,
                name = name
            )
        }
    }

    override suspend fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.BackClicked -> {}
            is HomeIntent.ErrorDialogDismissed -> {}
            is HomeIntent.OverviewTabClicked -> {
                setState { copy(overviewSelected = true) }
            }
            is HomeIntent.TipsTabClicked -> {
                setState { copy(overviewSelected = false) }
            }
        }
    }
}
