package fon.hakaton.fonhakatonandroidapp.presentation.utilities_edit_details

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
fun UtilitiesEditDetailsScreen(
    navController: NavController,
    viewModel: UtilitiesEditDetailsViewModel = hiltViewModel(),
) = viewModel.Collect { viewState, intentChannel, sideEffects ->
    UtilitiesEditDetailsSideEffects(sideEffects)
    UtilitiesEditDetailsScreen(viewState, intentChannel)
    UtilitiesEditDetailsDialog(viewState, intentChannel)
}

@Composable
private fun UtilitiesEditDetailsSideEffects(
    sideEffects: SharedFlow<UtilitiesEditDetailsSideEffect>
) {
    val context = LocalContext.current
    LaunchedEffect(sideEffects) {
        sideEffects.collect { sideEffect ->
            when (sideEffect) {
                is UtilitiesEditDetailsSideEffect.ShowMessage -> {
                    Toast.makeText(context, sideEffect.text, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
private fun UtilitiesEditDetailsScreen(
    viewState: UtilitiesEditDetailsViewState,
    intentChannel: MutableSharedFlow<UtilitiesEditDetailsIntent> = MutableSharedFlow()
) {
    Column(modifier = Modifier.padding(20.dp)) {
        Text("EDIT SCREEN")
    }
}

@Composable
private fun UtilitiesEditDetailsDialog(
    viewState: UtilitiesEditDetailsViewState,
    intentChannel: MutableSharedFlow<UtilitiesEditDetailsIntent>
) {

}

@Preview("UtilitiesEditDetails")
@Preview("UtilitiesEditDetails (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewUtilitiesEditDetailsScreen() {
    FonHakatonAndroidAppTheme {
        UtilitiesEditDetailsScreen(UtilitiesEditDetailsViewState())
    }
}
