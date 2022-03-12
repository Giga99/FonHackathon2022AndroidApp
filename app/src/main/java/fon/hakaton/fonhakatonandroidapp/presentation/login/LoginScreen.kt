package fon.hakaton.fonhakatonandroidapp.presentation.login

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fon.hakaton.fonhakatonandroidapp.R
import fon.hakaton.fonhakatonandroidapp.common.Destinations
import fon.hakaton.fonhakatonandroidapp.ui.theme.*
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGreenDark)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(R.drawable.city_background), contentDescription = "")
            Image(painter = painterResource(R.drawable.footprint), contentDescription = "")
        }
        Card(
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            modifier = Modifier.weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
            ) {
                Text(
                    text = stringResource(R.string.sign_in),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    textAlign = TextAlign.Center,
                    color = TextColorGray,
                    style = MaterialTheme.typography.h1
                )
                TextField(
                    value = viewState.email,
                    onValueChange = { intentChannel.tryEmit(LoginIntent.EmailInputChanged(it)) },
                    placeholder = { Text(text = stringResource(R.string.email)) },
                    isError = viewState.emailError != null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        placeholderColor = PlaceholderColor,
                        textColor = TextColorGray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                )
                TextField(
                    value = viewState.passwordText,
                    onValueChange = { intentChannel.tryEmit(LoginIntent.PasswordInputChanged(it)) },
                    placeholder = { Text(text = stringResource(R.string.password)) },
                    isError = viewState.emailError != null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        placeholderColor = PlaceholderColor,
                        textColor = TextColorGray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                )
                Spacer(modifier = Modifier.height(60.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ButtonLightGreen
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(20.dp),
                    onClick = { intentChannel.tryEmit(LoginIntent.LoginButtonClicked) },
                ) {
                    Text(
                        text = stringResource(R.string.sign_in),
                        color = Color.White,
                        style = MaterialTheme.typography.button,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(modifier = Modifier.weight(1f))
                    Text(
                        text = stringResource(R.string.or),
                        modifier = Modifier.padding(horizontal = 8.dp),
                        color = TextColorGray,
                    )
                    Divider(modifier = Modifier.weight(1f))
                }
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ButtonDarkGreen
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(20.dp),
                    onClick = { /* TODO add click reaction */ },
                ) {
                    Text(
                        text = stringResource(R.string.register),
                        color = Color.White,
                        style = MaterialTheme.typography.button,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
                Text(
                    text = stringResource(R.string.forgot_your_password),
                    color = TextColorGray,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 24.dp),
                    textAlign = TextAlign.Center
                )
            }
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
