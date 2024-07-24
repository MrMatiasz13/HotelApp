package pl.mrmatiasz.hotelapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pl.mrmatiasz.hotelapp.presentation.login_screen.LoginScreen
import pl.mrmatiasz.hotelapp.presentation.registration_screen.RegistrationScreen
import pl.mrmatiasz.hotelapp.presentation.room_list_screen.RoomListScreen

@Composable
fun Navigation(navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = RegistrationScreen
    ) {
        composable<RegistrationScreen> {
            RegistrationScreen(navController = navHostController)
        }

        composable<LoginScreen> {
            LoginScreen(navController = navHostController)
        }

        composable<RoomListScreen> {
            RoomListScreen(navController = navHostController)
        }
    }
}