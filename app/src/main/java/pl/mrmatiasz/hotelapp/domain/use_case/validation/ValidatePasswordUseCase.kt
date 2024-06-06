package pl.mrmatiasz.hotelapp.domain.use_case.validation

class ValidatePasswordUseCase {

    fun execute(password: String): ValidationResult {

        if(password.length < 8) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "Password needs to have at least 8 characters"
            )
        }

        return ValidationResult(
            isSuccess = true,
            errorMessage = null
        )
    }
}