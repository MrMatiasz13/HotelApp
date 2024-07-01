package pl.mrmatiasz.hotelapp.presentation.registration_screen

data class RegistrationFormState(
    val username: String = "",
    val usernameError: String? = null,

    val email: String = "",
    val emailError: String? = null,

    val password: String = "",
    val passwordError: String? = null,

    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null
)