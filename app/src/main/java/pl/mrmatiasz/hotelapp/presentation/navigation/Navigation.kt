package pl.mrmatiasz.hotelapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pl.mrmatiasz.hotelapp.presentation.registration_screen.RegistrationScreen

@Composable
fun Navigation(navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = RegistrationScreen
    ) {
        composable<RegistrationScreen> {
            RegistrationScreen(navController = navHostController)
        }

        composable<RoomListScreen> {
            RoomListScreen
        }
    }
}