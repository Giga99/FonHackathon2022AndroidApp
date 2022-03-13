package fon.hakaton.fonhakatonandroidapp.presentation.food_details

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
fun FoodDetailsScreen(
    navController: NavController,
    viewModel: FoodDetailsViewModel = hiltViewModel(),
) = viewModel.Collect { viewState, intentChannel, sideEffects ->
    FoodDetailsSideEffects(sideEffects)
    FoodDetailsScreen(viewState, intentChannel)
    FoodDetailsDialog(viewState, intentChannel)
}

@Composable
private fun FoodDetailsSideEffects(
    sideEffects: SharedFlow<FoodDetailsSideEffect>
) {
    val context = LocalContext.current
    LaunchedEffect(sideEffects) {
        sideEffects.collect { sideEffect ->
            when (sideEffect) {
                is FoodDetailsSideEffect.ShowMessage -> {
                    Toast.makeText(context, sideEffect.text, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
private fun FoodDetailsScreen(
    viewState: FoodDetailsViewState,
    intentChannel: MutableSharedFlow<FoodDetailsIntent> = MutableSharedFlow()
) {
    Column(modifier = Modifier.padding(20.dp)) {
        Text(text = "FOOD DETAILS!")
    }
}

@Composable
private fun FoodDetailsDialog(
    viewState: FoodDetailsViewState,
    intentChannel: MutableSharedFlow<FoodDetailsIntent>
) {

}

@Preview("FoodDetails")
@Preview("FoodDetails (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewFoodDetailsScreen() {
    FonHakatonAndroidAppTheme() {
        FoodDetailsScreen(FoodDetailsViewState())
    }
}
