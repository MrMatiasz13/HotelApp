package pl.mrmatiasz.hotelapp.domain.use_case.validation

class ValidateRepeatPasswordUseCase {

    fun execute(password: String, repeatedPassword: String): ValidationResult {

        if(password != repeatedPassword) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "Passwords doesn't matches."
            )
        }

        return ValidationResult(
            isSuccess = true,
            errorMessage = null
        )
    }
}