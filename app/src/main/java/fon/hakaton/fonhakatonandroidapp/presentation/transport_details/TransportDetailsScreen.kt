package fon.hakaton.fonhakatonandroidapp.presentation.transport_details

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import fon.hakaton.fonhakatonandroidapp.presentation.food_details.ConsumptionItem
import fon.hakaton.fonhakatonandroidapp.presentation.food_details.LegendItem
import fon.hakaton.fonhakatonandroidapp.ui.theme.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect

@Composable
fun TransportDetailsScreen(
    navController: NavController,
    viewModel: TransportDetailsViewModel = hiltViewModel(),
) = viewModel.Collect { viewState, intentChannel, sideEffects ->
    TransportDetailsSideEffects(sideEffects)
    TransportDetailsScreen(viewState, navController, intentChannel)
    TransportDetailsDialog(viewState, intentChannel)
}

@Composable
private fun TransportDetailsSideEffects(
    sideEffects: SharedFlow<TransportDetailsSideEffect>
) {
    val context = LocalContext.current
    LaunchedEffect(sideEffects) {
        sideEffects.collect { sideEffect ->
            when (sideEffect) {
                is TransportDetailsSideEffect.ShowMessage -> {
                    Toast.makeText(context, sideEffect.text, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
private fun TransportDetailsScreen(
    viewState: TransportDetailsViewState,
    navController: NavController,
    intentChannel: MutableSharedFlow<TransportDetailsIntent> = MutableSharedFlow()
) {
    Scaffold {
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
                                text = stringResource(R.string.transport),
                                color = Color.White,
                                style = MaterialTheme.typography.h1,
                            )
                            Text(
                                text = stringResource(
                                    R.string.kg_mo,
                                    viewState.transport.carbonFootprint,
                                ),
                                color = Color.White,
                                style = MaterialTheme.typography.h1,
                            )
                        }
                        Text(
                            text = stringResource(
                                R.string.better_than,
                                viewState.transport.betterThanPercent
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
                            consumption = viewState.transport.lastMonthConsumption,
                            unit = stringResource(R.string.kg)
                        )
                        ConsumptionItem(
                            description = stringResource(R.string.average_consumption),
                            consumption = viewState.transport.averageConsumption,
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
                                    description = stringResource(R.string.walk),
                                    color = ProgressBeefColor
                                )
                                LegendItem(
                                    description = stringResource(R.string.bicycle),
                                    color = ProgressOtherMeatColor
                                )
                                LegendItem(
                                    description = stringResource(R.string.car),
                                    color = ProgressAnimalProductsColor
                                )
                                LegendItem(
                                    description = stringResource(R.string.bus),
                                    color = ProgressVegetablesColor
                                )
                                LegendItem(
                                    description = stringResource(R.string.plane),
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
                        text = stringResource(R.string.suggested_routes),
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
                    SuggestedRoute()
                }
            }
        }
    }
}

@Composable
private fun SuggestedRoute() {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .background(TextInputGrayColor)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(R.drawable.start_destination),
                    contentDescription = "",
                    tint = ButtonLightGreen,
                    modifier = Modifier.size(26.dp)
                )
                Card(
                    modifier = Modifier
                        .size(6.dp)
                        .padding(top = 8.dp),
                    shape = CircleShape,
                    backgroundColor = TextColorGray
                ) {
                }
                Card(
                    modifier = Modifier
                        .size(6.dp)
                        .padding(top = 8.dp),
                    shape = CircleShape,
                    backgroundColor = TextColorGray
                ) {
                }
                Card(
                    modifier = Modifier
                        .size(6.dp)
                        .padding(top = 8.dp),
                    shape = CircleShape,
                    backgroundColor = TextColorGray
                ) {
                }
                Icon(
                    painter = painterResource(R.drawable.end_destination),
                    contentDescription = "",
                    tint = Color.Red,
                    modifier = Modifier
                        .size(26.dp)
                        .padding(top = 8.dp)
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "Elektrotehnicki fakultet",
                        modifier = Modifier
                            .background(Color.White)
                            .padding(8.dp),
                    )
                }
                Card(
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Fakultet organizacionih nauka",
                        modifier = Modifier
                            .background(Color.White)
                            .padding(8.dp),
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxHeight(),
            ) {
                Icon(
                    imageVector = MaterialTheme.icons.ArrowBackIos,
                    contentDescription = "",
                    modifier = Modifier.rotate(180f),
                    tint = ButtonLightGreen
                )
            }
        }
    }
}

@Composable
private fun CarbonProgressBar(
    viewState: TransportDetailsViewState
) {
    val zeroAngle = 0f
    val beginning = 270f
    val fullAngle = 360f
    val animationDuration = 1200
    val sum =
        (viewState.transport.walk + viewState.transport.bicycle + viewState.transport.car + viewState.transport.bus + viewState.transport.plane)

    val angleRatioBeef = remember {
        Animatable(zeroAngle)
    }
    LaunchedEffect(key1 = viewState.transport.walk) {
        angleRatioBeef.animateTo(
            targetValue = viewState.transport.walk.toFloat() / sum,
            animationSpec = tween(durationMillis = animationDuration)
        )
    }
    val angleToBeef = fullAngle * angleRatioBeef.value

    val angleRatioOtherMeat = remember {
        Animatable(zeroAngle)
    }
    LaunchedEffect(key1 = viewState.transport.bicycle) {
        angleRatioOtherMeat.animateTo(
            targetValue = viewState.transport.bicycle.toFloat() / sum,
            animationSpec = tween(durationMillis = animationDuration)
        )
    }
    val angleToOtherMeat = fullAngle * angleRatioOtherMeat.value

    val angleRatioAnimalProducts = remember {
        Animatable(zeroAngle)
    }
    LaunchedEffect(key1 = viewState.transport.car) {
        angleRatioAnimalProducts.animateTo(
            targetValue = viewState.transport.car.toFloat() / sum,
            animationSpec = tween(durationMillis = animationDuration)
        )
    }
    val angleToAnimalProducts = fullAngle * angleRatioAnimalProducts.value

    val angleRatioVegetables = remember {
        Animatable(zeroAngle)
    }
    LaunchedEffect(key1 = viewState.transport.bus) {
        angleRatioVegetables.animateTo(
            targetValue = viewState.transport.bus.toFloat() / sum,
            animationSpec = tween(durationMillis = animationDuration)
        )
    }
    val angleToVegetables = fullAngle * angleRatioVegetables.value

    val angleRatioPastry = remember {
        Animatable(zeroAngle)
    }
    LaunchedEffect(key1 = viewState.transport.plane) {
        angleRatioPastry.animateTo(
            targetValue = viewState.transport.plane.toFloat() / sum,
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
private fun TransportDetailsDialog(
    viewState: TransportDetailsViewState,
    intentChannel: MutableSharedFlow<TransportDetailsIntent>
) {

}

@Preview("TransportDetails")
@Preview("TransportDetails (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewTransportDetailsScreen() {
    FonHakatonAndroidAppTheme() {
//        TransportDetailsScreen(TransportDetailsViewState())
    }
}
