package fon.hakaton.fonhakatonandroidapp.presentation.login

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fon.hakaton.fonhakatonandroidapp.common.Destinations
import fon.hakaton.fonhakatonandroidapp.ui.theme.FonHakatonAndroidAppTheme
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) = viewModel.Collect { viewState, intentChannel, sideEffects ->
    LoginSideEffects(navController, sideEffects)
    LoginScreen(viewState, intentChannel)
    LoginDialog(viewState, intentChannel)
}

@Composable
private fun LoginSideEffects(
    navController: NavController,
    sideEffects: SharedFlow<LoginSideEffect>
) {
    val context = LocalContext.current
    LaunchedEffect(sideEffects) {
        sideEffects.collect { sideEffect ->
            when (sideEffect) {
                is LoginSideEffect.ShowMessage -> {
                    Toast.makeText(context, sideEffect.text, Toast.LENGTH_SHORT).show()
                }

                is LoginSideEffect.SuccessfulLogin -> {
                    navController.navigate(Destinations.HomeScreen.fullRoute)
                }
            }
        }
    }
}

@Composable
private fun LoginScreen(
    viewState: LoginViewState,
    intentChannel: MutableSharedFlow<LoginIntent> = MutableSharedFlow()
) {
    Column {
        TextField(
            value = viewState.email,
            onValueChange = { intentChannel.tryEmit(LoginIntent.EmailInputChanged(it)) },
            placeholder = { Text(text = "Email") },
            isError = viewState.emailError != null,
        )
        TextField(
            value = viewState.passwordText,
            onValueChange = { intentChannel.tryEmit(LoginIntent.PasswordInputChanged(it)) },
            placeholder = { Text(text = "Password") },
            isError = viewState.emailError != null,
        )
        Button(onClick = { intentChannel.tryEmit(LoginIntent.LoginButtonClicked) }) {
            Text(text = "Login")
        }
    }
}

@Composable
private fun LoginDialog(
    viewState: LoginViewState,
    intentChannel: MutableSharedFlow<LoginIntent>
) {

}

@Preview("Login")
@Preview("Login (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLoginScreen() {
    FonHakatonAndroidAppTheme {
        LoginScreen(LoginViewState())
    }
}
