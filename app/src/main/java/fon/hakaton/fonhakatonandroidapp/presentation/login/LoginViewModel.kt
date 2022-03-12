package fon.hakaton.fonhakatonandroidapp.presentation.login

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import fon.hakaton.fonhakatonandroidapp.common.AppViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : AppViewModel<LoginViewState, LoginIntent, LoginSideEffect>(
    viewState = LoginViewState()
) {

    init {

    }

    override suspend fun processIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.BackClicked -> {}
            is LoginIntent.ErrorDialogDismissed -> {}
        }
    }
}
