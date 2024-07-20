package pl.mrmatiasz.hotelapp.presentation.login_screen

data class LoginState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)