package fon.hakaton.fonhakatonandroidapp.presentation.login

sealed class LoginIntent {
    object ErrorDialogDismissed : LoginIntent()
    data class UsernameInputChanged(val username: String) : LoginIntent()
    data class PasswordInputChanged(val password: String) : LoginIntent()
    object LoginButtonClicked : LoginIntent()
}

sealed class LoginSideEffect {
    data class ShowMessage(val text: String) : LoginSideEffect()
    data class SuccessfulLogin(val id: Int, val username: String, val name: String) : LoginSideEffect()
}

data class LoginViewState(
    val username: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
)
