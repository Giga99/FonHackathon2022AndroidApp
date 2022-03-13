package fon.hakaton.fonhakatonandroidapp.presentation.food_details

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fon.hakaton.fonhakatonandroidapp.R
import fon.hakaton.fonhakatonandroidapp.presentation.tips.TipItem
import fon.hakaton.fonhakatonandroidapp.ui.theme.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect

@Composable
fun FoodDetailsScreen(
    navController: NavController,
    viewModel: FoodDetailsViewModel = hiltViewModel(),
) = viewModel.Collect { viewState, intentChannel, sideEffects ->
    FoodDetailsSideEffects(sideEffects)
    FoodDetailsScreen(viewState, navController, intentChannel)
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
    navController: NavController,
    intentChannel: MutableSharedFlow<FoodDetailsIntent> = MutableSharedFlow()
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                backgroundColor = ButtonDarkGreen,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Icon(
                    imageVector = MaterialTheme.icons.Edit,
                    contentDescription = "",
                    tint = Color.White,
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
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
                            Text(
                                text = stringResource(
                                    R.string.kg_mo,
                                    viewState.foodModel.carbonFootprint,
                                ),
                                color = Color.White,
                                style = MaterialTheme.typography.h1,
                            )
                        }
                        Text(
                            text = stringResource(
                                R.string.better_than,
                                viewState.foodModel.betterThanPercent
                            ),
                            color = Color.White,
                            style = MaterialTheme.typography.h4,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp)
                        )
                        Spacer(modifier = Modifier.height(36.dp))
                        ConsumptionItem(
                            description = stringResource(R.string.last_month),
                            consumption = viewState.foodModel.lastMonthConsumption,
                            unit = stringResource(R.string.kg)
                        )
                        ConsumptionItem(
                            description = stringResource(R.string.average_consumption),
                            consumption = viewState.foodModel.averageConsumption,
                            unit = stringResource(R.string.kg)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(36.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CarbonProgressBar(viewState)
                            Column(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(horizontal = 8.dp)
                            ) {
                                LegendItem(
                                    description = stringResource(R.string.beef),
                                    color = ProgressBeefColor
                                )
                                LegendItem(
                                    description = stringResource(R.string.other_meat),
                                    color = ProgressOtherMeatColor
                                )
                                LegendItem(
                                    description = stringResource(R.string.animal_products),
                                    color = ProgressAnimalProductsColor
                                )
                                LegendItem(
                                    description = stringResource(R.string.fruits_vegetables),
                                    color = ProgressVegetablesColor
                                )
                                LegendItem(
                                    description = stringResource(R.string.pastry),
                                    color = ProgressPastryColor
                                )
                            }
                        }
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(top = 24.dp, bottom = 48.dp)
                ) {
                    Text(
                        text = stringResource(R.string.tips),
                        color = TextColorDarkGray2,
                        style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        ),
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .padding(bottom = 16.dp)
                    )
                    TipItem(
                        title = viewState.foodModel.tip.title,
                        description = viewState.foodModel.tip.description,
                    )
                }
            }
        }
    }
}

@Composable
private fun LegendItem(
    description: String,
    color: Color,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(color)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.body1,
            color = Color.White,
        )
    }
}

@Composable
private fun CarbonProgressBar(
    viewState: FoodDetailsViewState
) {
    val zeroAngle = 0f
    val beginning = 270f
    val fullAngle = 360f
    val animationDuration = 1200

    val angleRatioBeef = remember {
        Animatable(zeroAngle)
    }
    LaunchedEffect(key1 = viewState.foodModel.beef) {
        angleRatioBeef.animateTo(
            targetValue = viewState.foodModel.beef.toFloat() / 1000f,
            animationSpec = tween(durationMillis = animationDuration)
        )
    }
    val angleToBeef = fullAngle * angleRatioBeef.value

    val angleRatioOtherMeat = remember {
        Animatable(zeroAngle)
    }
    LaunchedEffect(key1 = viewState.foodModel.otherMeat) {
        angleRatioOtherMeat.animateTo(
            targetValue = viewState.foodModel.otherMeat.toFloat() / 1000f,
            animationSpec = tween(durationMillis = animationDuration)
        )
    }
    val angleToOtherMeat = fullAngle * angleRatioOtherMeat.value

    val angleRatioAnimalProducts = remember {
        Animatable(zeroAngle)
    }
    LaunchedEffect(key1 = viewState.foodModel.animalProducts) {
        angleRatioAnimalProducts.animateTo(
            targetValue = viewState.foodModel.animalProducts.toFloat() / 1000f,
            animationSpec = tween(durationMillis = animationDuration)
        )
    }
    val angleToAnimalProducts = fullAngle * angleRatioAnimalProducts.value

    val angleRatioVegetables = remember {
        Animatable(zeroAngle)
    }
    LaunchedEffect(key1 = viewState.foodModel.vegetables) {
        angleRatioVegetables.animateTo(
            targetValue = viewState.foodModel.vegetables.toFloat() / 1000f,
            animationSpec = tween(durationMillis = animationDuration)
        )
    }
    val angleToVegetables = fullAngle * angleRatioVegetables.value

    val angleRatioPastry = remember {
        Animatable(zeroAngle)
    }
    LaunchedEffect(key1 = viewState.foodModel.pastry) {
        angleRatioPastry.animateTo(
            targetValue = viewState.foodModel.pastry.toFloat() / 1000f,
            animationSpec = tween(durationMillis = animationDuration)
        )
    }
    val angleToPastry = fullAngle * angleRatioPastry.value

    Canvas(
        modifier = Modifier
            .size(210.dp)
            .aspectRatio(1f)
    ) {
        drawArc(
            color = TextInputGrayColor,
            startAngle = zeroAngle,
            sweepAngle = fullAngle,
            useCenter = false,
            size = size,
            style = Stroke(width = 24.dp.toPx())
        )
        drawArc(
            color = ProgressBeefColor,
            startAngle = beginning,
            sweepAngle = angleToBeef,
            useCenter = false,
            size = size,
            style = Stroke(width = 24.dp.toPx())
        )
        drawArc(
            color = ProgressOtherMeatColor,
            startAngle = beginning + angleToBeef,
            sweepAngle = angleToOtherMeat,
            useCenter = false,
            size = size,
            style = Stroke(width = 24.dp.toPx())
        )
        drawArc(
            color = ProgressAnimalProductsColor,
            startAngle = beginning + angleToBeef + angleToOtherMeat,
            sweepAngle = angleToAnimalProducts,
            useCenter = false,
            size = size,
            style = Stroke(width = 24.dp.toPx())
        )
        drawArc(
            color = ProgressVegetablesColor,
            startAngle = beginning + angleToBeef + angleToOtherMeat + angleToAnimalProducts,
            sweepAngle = angleToVegetables,
            useCenter = false,
            size = size,
            style = Stroke(width = 24.dp.toPx())
        )
        drawArc(
            color = ProgressPastryColor,
            startAngle = beginning + angleToBeef + angleToOtherMeat + angleToAnimalProducts + angleToVegetables,
            sweepAngle = angleToPastry,
            useCenter = false,
            size = size,
            style = Stroke(width = 24.dp.toPx())
        )
    }
}

@Composable
private fun ConsumptionItem(
    description: String,
    consumption: Float,
    unit: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = description,
            color = Color.White,
            style = MaterialTheme.typography.h4,
        )
        Text(
            text = "$consumption $unit",
            color = Color.White,
            style = MaterialTheme.typography.h4,
        )
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
//        FoodDetailsScreen(FoodDetailsViewState())
    }
}
