package fon.hakaton.fonhakatonandroidapp.presentation.carbon_tracker

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fon.hakaton.fonhakatonandroidapp.R
import fon.hakaton.fonhakatonandroidapp.common.Destinations
import fon.hakaton.fonhakatonandroidapp.domain.models.CarbonOverviewModel
import fon.hakaton.fonhakatonandroidapp.ui.theme.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect

@Composable
fun CarbonTrackerScreen(
    id: Int,
    username: String,
    name: String,
    navController: NavController,
    viewModel: CarbonTrackerViewModel = hiltViewModel(),
) = viewModel.Collect { viewState, intentChannel, sideEffects ->
    viewModel.getData(id, username)

    CarbonTrackerSideEffects(sideEffects)
    CarbonTrackerScreen(id, username, name, viewState, navController, intentChannel)
    CarbonTrackerDialog(viewState, intentChannel)
}

@Composable
private fun CarbonTrackerSideEffects(
    sideEffects: SharedFlow<CarbonTrackerSideEffect>
) {
    val context = LocalContext.current
    LaunchedEffect(sideEffects) {
        sideEffects.collect { sideEffect ->
            when (sideEffect) {
                is CarbonTrackerSideEffect.ShowMessage -> {
                    Toast.makeText(context, sideEffect.text, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
private fun CarbonTrackerScreen(
    id: Int,
    username: String,
    name: String,
    viewState: CarbonTrackerViewState,
    navController: NavController,
    intentChannel: MutableSharedFlow<CarbonTrackerIntent> = MutableSharedFlow()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .padding(20.dp)
        ) {
            Text(
                text = stringResource(R.string.welcome_back, name),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4,
                color = TextColorDarkGray,
            )
            Icon(
                painter = painterResource(R.drawable.user),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                tint = ButtonLightGreen,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            CarbonProgressBar(viewState.carbonOverviewModel)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val carbonOverall = viewState.carbonOverviewModel.carbonOverall
                Text(
                    text = String.format("%.2f", carbonOverall),
                    color = Color.Black,
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = stringResource(R.string.co2_kg_month),
                    color = Color.Black,
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(horizontal = 8.dp)
            ) {
                LegendItem(
                    description = stringResource(R.string.transport),
                    color = ProgressTransportColor
                )
                LegendItem(
                    description = stringResource(R.string.water),
                    color = ProgressWaterColor
                )
                LegendItem(
                    description = stringResource(R.string.food),
                    color = ProgressFoodColor
                )
                LegendItem(
                    description = stringResource(R.string.electricity),
                    color = ProgressElectricityColor
                )
            }
        }

        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = stringResource(
                R.string.you_are_better_than,
                "${viewState.carbonOverviewModel.betterThanPercentage}%"
            ),
            color = Color.Black,
            style = MaterialTheme.typography.h2,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(36.dp))

        Card(
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            modifier = Modifier
                .weight(1f),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ButtonDarkGreen)
                    .padding(24.dp),
            ) {
                CarbonItem(
                    icon = painterResource(R.drawable.bolt),
                    name = stringResource(R.string.electricity),
                    value = stringResource(
                        R.string.kg_mo,
                        viewState.carbonOverviewModel.electricityAmount
                    ),
                    onClick = {
                        navController.navigate(
                            Destinations.UtilitiesDetailsScreen(
                                id = id,
                                username = username,
                                name = name,
                                isElectricity = true
                            )
                        )
                    }
                )
                CarbonItem(
                    icon = painterResource(R.drawable.drop),
                    name = stringResource(R.string.water),
                    value = stringResource(
                        R.string.kg_mo,
                        viewState.carbonOverviewModel.waterAmount
                    ),
                    onClick = {
                        navController.navigate(
                            Destinations.UtilitiesDetailsScreen(
                                id = id,
                                username = username,
                                name = name,
                                isElectricity = false,
                            )
                        )
                    }
                )
                CarbonItem(
                    icon = painterResource(R.drawable.car),
                    name = stringResource(R.string.transport),
                    value = stringResource(
                        R.string.kg_mo,
                        viewState.carbonOverviewModel.transportAmount
                    ),
                    onClick = { navController.navigate(Destinations.TransportDetailsScreen.fullRoute) }
                )
                CarbonItem(
                    icon = painterResource(R.drawable.fork_knife),
                    name = stringResource(R.string.food),
                    value = stringResource(
                        R.string.kg_mo,
                        viewState.carbonOverviewModel.foodAmount
                    ),
                    showDivider = false,
                    onClick = { navController.navigate(Destinations.FoodDetailsScreen.fullRoute) }
                )
            }
        }
    }
}

@Composable
private fun CarbonItem(
    icon: Painter,
    name: String,
    value: String,
    showDivider: Boolean = true,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ButtonDarkGreen),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(ButtonDarkGreen)
                .clickable(onClick = onClick),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = icon,
                contentDescription = "",
                tint = ButtonLightGreen,
                modifier = Modifier.size(30.dp)
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = name,
                    color = ButtonLightGreen
                )
                Text(
                    text = value,
                    color = Color.White,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Icon(
                imageVector = MaterialTheme.icons.ArrowBackIos,
                contentDescription = "",
                modifier = Modifier.rotate(180f),
                tint = ButtonLightGreen
            )
        }
        if (showDivider) {
            Divider(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .background(DividerColor),
            )
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
            style = MaterialTheme.typography.body2,
            color = TextColorGray,
        )
    }
}

@Composable
private fun CarbonProgressBar(
    carbonOverviewModel: CarbonOverviewModel
) {
    val zeroAngle = 0f
    val beginning = 270f
    val fullAngle = 360f
    val animationDuration = 1200
    val sum =
        (carbonOverviewModel.transportAmount + carbonOverviewModel.foodAmount + carbonOverviewModel.waterAmount + carbonOverviewModel.electricityAmount)

    val angleRatioTransport = remember {
        Animatable(zeroAngle)
    }
    LaunchedEffect(key1 = carbonOverviewModel.transportAmount) {
        angleRatioTransport.animateTo(
            targetValue = carbonOverviewModel.transportAmount / sum,
            animationSpec = tween(durationMillis = animationDuration)
        )
    }
    val angleToTransport = fullAngle * angleRatioTransport.value

    val angleRatioWater = remember {
        Animatable(zeroAngle)
    }
    LaunchedEffect(key1 = carbonOverviewModel.waterAmount) {
        angleRatioWater.animateTo(
            targetValue = carbonOverviewModel.waterAmount / sum,
            animationSpec = tween(durationMillis = animationDuration)
        )
    }
    val angleToWater = fullAngle * angleRatioWater.value

    val angleRatioFood = remember {
        Animatable(zeroAngle)
    }
    LaunchedEffect(key1 = carbonOverviewModel.foodAmount) {
        angleRatioFood.animateTo(
            targetValue = carbonOverviewModel.foodAmount / sum,
            animationSpec = tween(durationMillis = animationDuration)
        )
    }
    val angleToFood = fullAngle * angleRatioFood.value

    val angleRatioElectricity = remember {
        Animatable(zeroAngle)
    }
    LaunchedEffect(key1 = carbonOverviewModel.electricityAmount) {
        angleRatioElectricity.animateTo(
            targetValue = carbonOverviewModel.electricityAmount / sum,
            animationSpec = tween(durationMillis = animationDuration)
        )
    }
    val angleToElectricity = fullAngle * angleRatioElectricity.value

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
            color = ProgressTransportColor,
            startAngle = beginning,
            sweepAngle = angleToTransport,
            useCenter = false,
            size = size,
            style = Stroke(width = 24.dp.toPx())
        )
        drawArc(
            color = ProgressWaterColor,
            startAngle = beginning + angleToTransport,
            sweepAngle = angleToWater,
            useCenter = false,
            size = size,
            style = Stroke(width = 24.dp.toPx())
        )
        drawArc(
            color = ProgressFoodColor,
            startAngle = beginning + angleToTransport + angleToWater,
            sweepAngle = angleToFood,
            useCenter = false,
            size = size,
            style = Stroke(width = 24.dp.toPx())
        )
        drawArc(
            color = ProgressElectricityColor,
            startAngle = beginning + angleToTransport + angleToWater + angleToFood,
            sweepAngle = angleToElectricity,
            useCenter = false,
            size = size,
            style = Stroke(width = 24.dp.toPx())
        )
    }
}

@Composable
private fun CarbonTrackerDialog(
    viewState: CarbonTrackerViewState,
    intentChannel: MutableSharedFlow<CarbonTrackerIntent>
) {

}

@Preview("CarbonTracker")
@Preview("CarbonTracker (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewCarbonTrackerScreen() {
    FonHakatonAndroidAppTheme() {
//        CarbonTrackerScreen(CarbonTrackerViewState())
    }
}
