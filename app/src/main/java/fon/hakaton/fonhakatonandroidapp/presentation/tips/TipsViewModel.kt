package fon.hakaton.fonhakatonandroidapp.presentation.tips

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import fon.hakaton.fonhakatonandroidapp.common.AppViewModel
import javax.inject.Inject

@HiltViewModel
class TipsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : AppViewModel<TipsViewState, TipsIntent, TipsSideEffect>(
    viewState = TipsViewState()
) {

    init {

    }

    override suspend fun processIntent(intent: TipsIntent) {
        when (intent) {
            is TipsIntent.BackClicked -> {}
            is TipsIntent.ErrorDialogDismissed -> {}
        }
    }
}
