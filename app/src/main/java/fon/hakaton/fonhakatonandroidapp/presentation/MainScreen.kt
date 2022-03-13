package fon.hakaton.fonhakatonandroidapp.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fon.hakaton.fonhakatonandroidapp.common.Destinations
import fon.hakaton.fonhakatonandroidapp.presentation.food_details.FoodDetailsScreen
import fon.hakaton.fonhakatonandroidapp.presentation.food_edit_details.FoodEditDetailsScreen
import fon.hakaton.fonhakatonandroidapp.presentation.home.HomeScreen
import fon.hakaton.fonhakatonandroidapp.presentation.login.LoginScreen
import fon.hakaton.fonhakatonandroidapp.presentation.utilities_details.UtilitiesDetailsScreen
import fon.hakaton.fonhakatonandroidapp.presentation.utilities_edit_details.UtilitiesEditDetailsScreen
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
                .fillMaxSize(),
        ) {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Destinations.LoginScreen.fullRoute
            ) {
                composable(route = Destinations.LoginScreen.fullRoute) {
                    LoginScreen(navController = navController)
                }

                composable(route = Destinations.HomeScreen.fullRoute) {
                    HomeScreen(navController = navController)
                }

                composable(route = Destinations.UtilitiesDetailsScreen.fullRoute) {
                    UtilitiesDetailsScreen(navController = navController)
                }

                composable(route = Destinations.UtilitiesEditDetailsScreen.fullRoute) {
                    UtilitiesEditDetailsScreen(navController = navController)
                }

                composable(route = Destinations.FoodDetailsScreen.fullRoute) {
                    FoodDetailsScreen(navController = navController)
                }

                composable(route = Destinations.FoodEditDetailsScreen.fullRoute) {
                    FoodEditDetailsScreen(navController = navController)
                }
            }
        }
    }
}
