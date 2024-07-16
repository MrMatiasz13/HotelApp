package pl.mrmatiasz.hotelapp.presentation.registration_screen

data class RegistrationState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)