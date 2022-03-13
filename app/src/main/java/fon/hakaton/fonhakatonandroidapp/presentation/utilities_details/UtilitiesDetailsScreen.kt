package fon.hakaton.fonhakatonandroidapp.presentation.utilities_details

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import fon.hakaton.fonhakatonandroidapp.common.Destinations
import fon.hakaton.fonhakatonandroidapp.common.simpleChartAnimation
import fon.hakaton.fonhakatonandroidapp.domain.models.toBarData
import fon.hakaton.fonhakatonandroidapp.presentation.tips.TipItem
import fon.hakaton.fonhakatonandroidapp.ui.bar.BarChart
import fon.hakaton.fonhakatonandroidapp.ui.bar.BarChartData
import fon.hakaton.fonhakatonandroidapp.ui.theme.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect

@Composable
fun UtilitiesDetailsScreen(
    navController: NavController,
    viewModel: UtilitiesDetailsViewModel = hiltViewModel(),
) = viewModel.Collect { viewState, intentChannel, sideEffects ->
    UtilitiesDetailsSideEffects(sideEffects)
    UtilitiesDetailsScreen(viewState, navController, intentChannel)
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
    navController: NavController,
    intentChannel: MutableSharedFlow<UtilitiesDetailsIntent> = MutableSharedFlow()
) {
    Scaffold(
        modifier = Modifier
            .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(
                        Destinations.UtilitiesEditDetailsScreen(
                            viewState.utility.name,
                            viewState.utility.lastMonthConsumption,
                            if (viewState.utility.isElectricity) viewState.utility.renewableEnergyPercent else null
                        )
                    )
                },
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
        Column(modifier = Modifier.fillMaxSize()) {
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
                            text = viewState.utility.name,
                            color = Color.White,
                            style = MaterialTheme.typography.h1,
                        )
                        Text(
                            text = stringResource(
                                R.string.kg_mo,
                                viewState.utility.carbonFootprint.toString(),
                            ),
                            color = Color.White,
                            style = MaterialTheme.typography.h1,
                        )
                    }
                    Text(
                        text = stringResource(
                            R.string.better_than,
                            viewState.utility.betterThanPercent
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
                        consumption = viewState.utility.lastMonthConsumption,
                        unit = stringResource(if (viewState.utility.isElectricity) R.string.electricity_unit else R.string.water_unit)
                    )
                    ConsumptionItem(
                        description = stringResource(R.string.average_consumption),
                        consumption = viewState.utility.averageConsumption,
                        unit = stringResource(if (viewState.utility.isElectricity) R.string.electricity_unit else R.string.water_unit)
                    )

                    BarChart(
                        barChartData = BarChartData(
                            bars = viewState.utility.lastFiveMonths.map { it.toBarData() }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(top = 24.dp),
                        animation = simpleChartAnimation(),
                    )
                }
            }
            Column(
                modifier = Modifier.padding(top = 24.dp)
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
                    title = viewState.utility.tip.title,
                    description = viewState.utility.tip.description,
                )
            }
        }
    }
}

@Composable
private fun ConsumptionItem(
    description: String,
    consumption: Long,
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
//        UtilitiesDetailsScreen(UtilitiesDetailsViewState())
    }
}
