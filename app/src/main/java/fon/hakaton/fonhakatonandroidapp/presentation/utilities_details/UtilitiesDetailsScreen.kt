package fon.hakaton.fonhakatonandroidapp.presentation.utilities_details

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fon.hakaton.fonhakatonandroidapp.ui.theme.FonHakatonAndroidAppTheme
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect

@Composable
fun UtilitiesDetailsScreen(
    navController: NavController,
    viewModel: UtilitiesDetailsViewModel = hiltViewModel(),
) = viewModel.Collect { viewState, intentChannel, sideEffects ->
    UtilitiesDetailsSideEffects(sideEffects)
    UtilitiesDetailsScreen(viewState, intentChannel)
    UtilitiesDetailsDialog(viewState, intentChannel)
}

@Composable
private fun UtilitiesDetailsSideEffects(
    sideEffects: SharedFlow<UtilitiesDetailsSideEffect>
) {
    val context = LocalContext.current
    LaunchedEffect(sideEffects) {
        sideEffects.collect { sideEffect ->
            when (sideEffect) {
                is UtilitiesDetailsSideEffect.ShowMessage -> {
                    Toast.makeText(context, sideEffect.text, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
private fun UtilitiesDetailsScreen(
    viewState: UtilitiesDetailsViewState,
    intentChannel: MutableSharedFlow<UtilitiesDetailsIntent> = MutableSharedFlow()
) {
    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text(text = viewState.utility.name)
    }
}

@Composable
private fun UtilitiesDetailsDialog(
    viewState: UtilitiesDetailsViewState,
    intentChannel: MutableSharedFlow<UtilitiesDetailsIntent>
) {

}

@Preview("UtilitiesDetails")
@Preview("UtilitiesDetails (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewUtilitiesDetailsScreen() {
    FonHakatonAndroidAppTheme() {
        UtilitiesDetailsScreen(UtilitiesDetailsViewState())
    }
}
