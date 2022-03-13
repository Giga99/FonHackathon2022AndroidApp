package fon.hakaton.fonhakatonandroidapp.presentation.food_edit_details

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fon.hakaton.fonhakatonandroidapp.R
import fon.hakaton.fonhakatonandroidapp.ui.theme.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect

@Composable
fun FoodEditDetailsScreen(
    navController: NavController,
    viewModel: FoodEditDetailsViewModel = hiltViewModel(),
) = viewModel.Collect { viewState, intentChannel, sideEffects ->
    FoodEditDetailsSideEffects(navController, sideEffects)
    FoodEditDetailsScreen(viewState, navController, intentChannel)
    FoodEditDetailsDialog(viewState, intentChannel)
}

@Composable
private fun FoodEditDetailsSideEffects(
    navController: NavController,
    sideEffects: SharedFlow<FoodEditDetailsSideEffect>
) {
    val context = LocalContext.current
    LaunchedEffect(sideEffects) {
        sideEffects.collect { sideEffect ->
            when (sideEffect) {
                is FoodEditDetailsSideEffect.ShowMessage -> {
                    Toast.makeText(context, sideEffect.text, Toast.LENGTH_SHORT).show()
                }

                is FoodEditDetailsSideEffect.InputFinished -> {
                    navController.popBackStack()
                }
            }
        }
    }
}

@Composable
private fun FoodEditDetailsScreen(
    viewState: FoodEditDetailsViewState,
    navController: NavController,
    intentChannel: MutableSharedFlow<FoodEditDetailsIntent> = MutableSharedFlow()
) {
    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White)
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .background(ButtonDarkGreen)
                            .padding(horizontal = 20.dp, vertical = 30.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .clickable(onClick = { navController.popBackStack() })
                        ) {
                            Icon(
                                imageVector = MaterialTheme.icons.ArrowBackIos,
                                contentDescription = "",
                                tint = ButtonLightGreen
                            )
                            Text(
                                text = stringResource(R.string.back),
                                color = ButtonLightGreen,
                                style = MaterialTheme.typography.h4
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(R.string.food),
                                color = Color.White,
                                style = MaterialTheme.typography.h1,
                            )
                        }
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .padding(vertical = 36.dp, horizontal = 24.dp)
                ) {
                    SliderItem(
                        name = stringResource(R.string.beef),
                        currentValue = viewState.beef.toFloat(),
                        onFinish = {}
                    )
                    SliderItem(
                        name = stringResource(R.string.other_meat),
                        currentValue = viewState.otherMeat.toFloat(),
                        onFinish = {}
                    )
                    SliderItem(
                        name = stringResource(R.string.animal_products_eggs_milk),
                        currentValue = viewState.animalProducts.toFloat(),
                        onFinish = {}
                    )
                    SliderItem(
                        name = stringResource(R.string.fruits_vegetables),
                        currentValue = viewState.vegetables.toFloat(),
                        onFinish = {}
                    )
                    SliderItem(
                        name = stringResource(R.string.pastry),
                        currentValue = viewState.pastry.toFloat(),
                        onFinish = {}
                    )

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = ButtonLightGreen
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 36.dp),
                        shape = RoundedCornerShape(20.dp),
                        onClick = { intentChannel.tryEmit(FoodEditDetailsIntent.SaveInputClicked) },
                    ) {
                        Text(
                            text = stringResource(R.string.save_input),
                            color = Color.White,
                            style = MaterialTheme.typography.button,
                            modifier = Modifier
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SliderItem(
    name: String,
    currentValue: Float,
    onFinish: (Float) -> Unit
) {
    var sliderPosition by remember { mutableStateOf(currentValue) }
    Column(
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Text(
            text = name,
            color = TextColorGray,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(horizontal = 7.dp)
        )
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            onValueChangeFinished = { onFinish(sliderPosition) },
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = ButtonDarkGreen,
                inactiveTrackColor = InactiveSlideColor,
            ),
            valueRange = 0f..500f,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 7.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "0",
                color = TextColorGray,
                style = MaterialTheme.typography.h4
            )
            Text(
                text = sliderPosition.toInt().toString(),
                color = TextColorGray,
                style = MaterialTheme.typography.h4,
            )
            Text(
                text = ">500g",
                color = TextColorGray,
                style = MaterialTheme.typography.h4,
            )
        }
    }
}

@Composable
private fun FoodEditDetailsDialog(
    viewState: FoodEditDetailsViewState,
    intentChannel: MutableSharedFlow<FoodEditDetailsIntent>
) {

}

@Preview("FoodEditDetails")
@Preview("FoodEditDetails (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewFoodEditDetailsScreen() {
    FonHakatonAndroidAppTheme() {
//        FoodEditDetailsScreen(FoodEditDetailsViewState())
    }
}
