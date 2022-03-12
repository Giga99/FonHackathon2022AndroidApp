package fon.hakaton.fonhakatonandroidapp.presentation.home

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fon.hakaton.fonhakatonandroidapp.ui.theme.FonHakatonAndroidAppTheme
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) = viewModel.Collect { viewState, intentChannel, sideEffects ->
    HomeSideEffects(sideEffects)
    HomeScreen(viewState, intentChannel)
    HomeDialog(viewState, intentChannel)
}

@Composable
private fun HomeSideEffects(
    sideEffects: SharedFlow<HomeSideEffect>
) {
    val context = LocalContext.current
    LaunchedEffect(sideEffects) {
        sideEffects.collect { sideEffect ->
            when (sideEffect) {
                is HomeSideEffect.ShowMessage -> {
                    Toast.makeText(context, sideEffect.text, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
private fun HomeScreen(
    viewState: HomeViewState,
    intentChannel: MutableSharedFlow<HomeIntent> = MutableSharedFlow()
) {
    Text(text = "WELCOME TO HOME!")
}

@Composable
private fun HomeDialog(
    viewState: HomeViewState,
    intentChannel: MutableSharedFlow<HomeIntent>
) {

}

@Preview("Home")
@Preview("Home (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewHomeScreen() {
    FonHakatonAndroidAppTheme {
        HomeScreen(HomeViewState())
    }
}
