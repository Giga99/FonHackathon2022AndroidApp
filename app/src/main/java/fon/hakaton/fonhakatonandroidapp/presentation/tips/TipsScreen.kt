package fon.hakaton.fonhakatonandroidapp.presentation.tips

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
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
import fon.hakaton.fonhakatonandroidapp.ui.theme.ButtonDarkGreen
import fon.hakaton.fonhakatonandroidapp.ui.theme.FonHakatonAndroidAppTheme
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect

@Composable
fun TipsScreen(
    navController: NavController,
    viewModel: TipsViewModel = hiltViewModel(),
) = viewModel.Collect { viewState, intentChannel, sideEffects ->
    TipsSideEffects(sideEffects)
    TipsScreen(viewState, intentChannel)
    TipsDialog(viewState, intentChannel)
}

@Composable
private fun TipsSideEffects(
    sideEffects: SharedFlow<TipsSideEffect>
) {
    val context = LocalContext.current
    LaunchedEffect(sideEffects) {
        sideEffects.collect { sideEffect ->
            when (sideEffect) {
                is TipsSideEffect.ShowMessage -> {
                    Toast.makeText(context, sideEffect.text, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
private fun TipsScreen(
    viewState: TipsViewState,
    intentChannel: MutableSharedFlow<TipsIntent> = MutableSharedFlow()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Card(
            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ButtonDarkGreen)
            ) {
                Text(
                    text = stringResource(R.string.tips),
                    color = Color.White,
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    ),
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding(bottom = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun TipsDialog(
    viewState: TipsViewState,
    intentChannel: MutableSharedFlow<TipsIntent>
) {

}

@Preview("Tips")
@Preview("Tips (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewTipsScreen() {
    FonHakatonAndroidAppTheme() {
        TipsScreen(TipsViewState())
    }
}
