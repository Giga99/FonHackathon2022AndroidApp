package fon.hakaton.fonhakatonandroidapp.presentation.login

sealed class LoginIntent {
    object ErrorDialogDismissed : LoginIntent()
    object BackClicked : LoginIntent()
}

sealed class LoginSideEffect {
    data class ShowMessage(val text: String) : LoginSideEffect()
}

data class LoginViewState(
    val temp: Int = 0
)
