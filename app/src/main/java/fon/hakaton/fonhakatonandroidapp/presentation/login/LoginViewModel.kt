package fon.hakaton.fonhakatonandroidapp.presentation.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fon.hakaton.fonhakatonandroidapp.common.AppViewModel
import fon.hakaton.fonhakatonandroidapp.common.Result
import fon.hakaton.fonhakatonandroidapp.domain.models.LoginModel
import fon.hakaton.fonhakatonandroidapp.domain.repos.LoginRepo
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepo: LoginRepo,
    savedStateHandle: SavedStateHandle
) : AppViewModel<LoginViewState, LoginIntent, LoginSideEffect>(
    viewState = LoginViewState()
) {

    override suspend fun processIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.ErrorDialogDismissed -> {}
            is LoginIntent.EmailInputChanged -> {
                setState { copy(email = intent.email) }
            }
            is LoginIntent.PasswordInputChanged -> {
                setState {
                    copy(password = intent.password)
                }
            }
            is LoginIntent.LoginButtonClicked -> {
                if (validateEmail(getState().email)) {
                    setState { copy(emailError = null) }
                    viewModelScope.launch {
                        val result =
                            loginRepo.login(LoginModel(getState().email, getState().password))
                        when (result) {
                            is Result.Success -> {
                                _sideEffects.tryEmit(LoginSideEffect.SuccessfulLogin)
                            }
                            is Result.Error -> {
                                Timber.d(result.message)
                            }
                        }
                    }
                } else {
                    setState { copy(emailError = "Bad email format!") }
                }
            }
        }
    }

    private fun validateEmail(email: String) =
        email.contains("@") && email.contains(".com")
}
