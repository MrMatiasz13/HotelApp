package pl.mrmatiasz.hotelapp.domain.use_case.validation

data class ValidationResult(
    val isSuccess: Boolean,
    val errorMessage: String? = ""
)
