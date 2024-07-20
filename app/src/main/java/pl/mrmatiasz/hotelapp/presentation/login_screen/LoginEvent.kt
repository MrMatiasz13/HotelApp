package pl.mrmatiasz.hotelapp.presentation.login_screen

sealed class LoginEvent {
    data class EmailChanged(val email: String): LoginEvent()
    data class PasswordChanged(val password: String): LoginEvent()

    data object Submit: LoginEvent()
}