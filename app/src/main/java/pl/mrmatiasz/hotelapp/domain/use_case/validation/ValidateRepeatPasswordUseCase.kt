package pl.mrmatiasz.hotelapp.domain.use_case.validation

class ValidateRepeatPasswordUseCase {

    fun execute(repeatPassword: String, password: String): ValidationResult {

        if(repeatPassword != password) {
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