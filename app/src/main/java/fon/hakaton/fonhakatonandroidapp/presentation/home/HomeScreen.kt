package fon.hakaton.fonhakatonandroidapp.presentation.home

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fon.hakaton.fonhakatonandroidapp.R
import fon.hakaton.fonhakatonandroidapp.presentation.carbon_tracker.CarbonTrackerScreen
import fon.hakaton.fonhakatonandroidapp.presentation.tips.TipsScreen
import fon.hakaton.fonhakatonandroidapp.ui.theme.ButtonDarkGreen
import fon.hakaton.fonhakatonandroidapp.ui.theme.ButtonLightGreen
import fon.hakaton.fonhakatonandroidapp.ui.theme.FonHakatonAndroidAppTheme
import fon.hakaton.fonhakatonandroidapp.ui.theme.TextInputGrayColor
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) = viewModel.Collect { viewState, intentChannel, sideEffects ->
    HomeSideEffects(sideEffects)
    HomeScreen(viewState, navController, intentChannel)
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
    navController: NavController,
    intentChannel: MutableSharedFlow<HomeIntent> = MutableSharedFlow()
) {
    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(ButtonDarkGreen)
                        .padding(top = 16.dp, bottom = 24.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TabItem(
                        name = stringResource(R.string.overview),
                        icon = painterResource(R.drawable.eye),
                        color = if (viewState.overviewSelected) ButtonLightGreen else TextInputGrayColor,
                        onClick = { intentChannel.tryEmit(HomeIntent.OverviewTabClicked) },
                    )
                    TabItem(
                        name = stringResource(R.string.fun_facts),
                        icon = painterResource(R.drawable.magazine),
                        color = if (!viewState.overviewSelected) ButtonLightGreen else TextInputGrayColor,
                        onClick = { intentChannel.tryEmit(HomeIntent.TipsTabClicked) },
                    )
                }
            }
        }
    ) {
        if (viewState.overviewSelected) {
            CarbonTrackerScreen(navController = navController)
        } else {
            TipsScreen(navController = navController)
        }
    }
}

@Composable
private fun TabItem(
    name: String,
    icon: Painter,
    color: Color,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Icon(
            painter = icon,
            contentDescription = "",
            modifier = Modifier.size(20.dp),
            tint = color
        )
        Text(
            text = name,
            modifier = Modifier
                .padding(top = 4.dp),
            color = color,
        )
    }
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
//        HomeScreen(HomeViewState())
    }
}
