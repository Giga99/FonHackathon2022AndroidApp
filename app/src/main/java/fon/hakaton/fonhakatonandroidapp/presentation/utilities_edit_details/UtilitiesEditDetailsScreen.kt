package fon.hakaton.fonhakatonandroidapp.presentation.utilities_edit_details

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
fun UtilitiesEditDetailsScreen(
    navController: NavController,
    viewModel: UtilitiesEditDetailsViewModel = hiltViewModel(),
) = viewModel.Collect { viewState, intentChannel, sideEffects ->
    UtilitiesEditDetailsSideEffects(navController, sideEffects)
    UtilitiesEditDetailsScreen(viewState, navController, intentChannel)
    UtilitiesEditDetailsDialog(viewState, intentChannel)
}

@Composable
private fun UtilitiesEditDetailsSideEffects(
    navController: NavController,
    sideEffects: SharedFlow<UtilitiesEditDetailsSideEffect>
) {
    val context = LocalContext.current
    LaunchedEffect(sideEffects) {
        sideEffects.collect { sideEffect ->
            when (sideEffect) {
                is UtilitiesEditDetailsSideEffect.ShowMessage -> {
                    Toast.makeText(context, sideEffect.text, Toast.LENGTH_SHORT).show()
                }

                is UtilitiesEditDetailsSideEffect.InputFinished -> {
                    navController.popBackStack(Destinations.HomeScreen.fullRoute, inclusive = false)
                }
            }
        }
    }
}

@Composable
private fun UtilitiesEditDetailsScreen(
    viewState: UtilitiesEditDetailsViewState,
    navController: NavController,
    intentChannel: MutableSharedFlow<UtilitiesEditDetailsIntent> = MutableSharedFlow()
) {
    Scaffold(
        modifier = Modifier
            .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical),
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
                            text = viewState.name,
                            color = Color.White,
                            style = MaterialTheme.typography.h1,
                        )
                    }
                }
            }

            Text(
                text = stringResource(R.string.number_of_residents),
                color = PlaceholderColor,
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp)
            )
            TextField(
                value = viewState.numberOfResidents.toString(),
                onValueChange = {
                    intentChannel.tryEmit(
                        UtilitiesEditDetailsIntent.NumberOfResidentsInputChanged(
                            it
                        )
                    )
                },
                placeholder = { Text(text = stringResource(R.string.number_of_residents)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(
                    placeholderColor = PlaceholderColor,
                    textColor = TextColorGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    backgroundColor = TextInputGrayColor,
                ),
            )

            Text(
                text = stringResource(R.string.apartment_size),
                color = PlaceholderColor,
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp)
            )
            TextField(
                value = viewState.apartmentSize.toString(),
                onValueChange = {
                    intentChannel.tryEmit(
                        UtilitiesEditDetailsIntent.ApartmentSizeInputChanged(
                            it
                        )
                    )
                },
                placeholder = { Text(text = stringResource(R.string.apartment_size)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(
                    placeholderColor = PlaceholderColor,
                    textColor = TextColorGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    backgroundColor = TextInputGrayColor,
                ),
            )


            Text(
                text = stringResource(R.string.consumption),
                color = PlaceholderColor,
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp)
            )
            TextField(
                value = viewState.consumption.toString(),
                onValueChange = {
                    intentChannel.tryEmit(
                        UtilitiesEditDetailsIntent.ConsumptionInputChanged(it)
                    )
                },
                placeholder = { Text(text = stringResource(R.string.electricity_unit)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(
                    placeholderColor = PlaceholderColor,
                    textColor = TextColorGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    backgroundColor = TextInputGrayColor,
                ),
            )

            viewState.renewableEnergyPercent?.let {
                Text(
                    text = stringResource(R.string.renewable_energy),
                    color = PlaceholderColor,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .padding(horizontal = 16.dp)
                )
                TextField(
                    value = viewState.renewableEnergyPercent.toString(),
                    onValueChange = {
                        intentChannel.tryEmit(
                            UtilitiesEditDetailsIntent.RenewableEnergyInputChanged(it)
                        )
                    },
                    placeholder = { Text(text = stringResource(R.string.renewable_energy)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        placeholderColor = PlaceholderColor,
                        textColor = TextColorGray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        backgroundColor = TextInputGrayColor,
                    ),
                )
            }

            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ButtonLightGreen
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 36.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { intentChannel.tryEmit(UtilitiesEditDetailsIntent.SaveInputClicked) },
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
//        UtilitiesEditDetailsScreen(UtilitiesEditDetailsViewState())
    }
}
