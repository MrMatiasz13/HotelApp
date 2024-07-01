package pl.mrmatiasz.hotelapp.presentation.registration_screen

sealed class RegistrationEvent {
    data class UsernameChanged(val username: String): RegistrationEvent()
    data class EmailChanged(val email: String): RegistrationEvent()
    data class PasswordChanged(val password: String): RegistrationEvent()
    data class RepeatedPasswordChanged(val repeatedPassword: String): RegistrationEvent()

    data object Submit: RegistrationEvent()
}