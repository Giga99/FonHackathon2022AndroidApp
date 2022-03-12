package fon.hakaton.fonhakatonandroidapp.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fon.hakaton.fonhakatonandroidapp.common.Destinations
import fon.hakaton.fonhakatonandroidapp.presentation.login.LoginScreen
import fon.hakaton.fonhakatonandroidapp.ui.theme.FonHakatonAndroidAppTheme

/**
 * @author igorstevanovic
 * Created 12.3.22. at 20:07
 */
@Composable
fun MainScreen() {
    FonHakatonAndroidAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(36.dp),
            color = MaterialTheme.colors.background
        ) {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Destinations.Login.fullRoute
            ) {
                composable(route = Destinations.Login.fullRoute) {
                    LoginScreen(navController = navController)
                }
            }
        }
    }
}
