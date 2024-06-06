package pl.mrmatiasz.hotelapp.domain.use_case.validation

class ValidateUsernameUseCase {

    fun execute(username: String): ValidationResult {
        if(username.isBlank()) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "Username can't be black."
            )
        }

        if(username.firstOrNull()?.isUpperCase() == false) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "Username need to start with upper-case."
            )
        }

        return ValidationResult(
            isSuccess = true,
            errorMessage = null
        )
    }
}