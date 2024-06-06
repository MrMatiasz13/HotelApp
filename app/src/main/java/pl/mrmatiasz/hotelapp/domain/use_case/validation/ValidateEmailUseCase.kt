package pl.mrmatiasz.hotelapp.domain.use_case.validation

class ValidateEmailUseCase {

    fun execute(email: String): ValidationResult {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"

        if(email.isBlank()) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "Email can't be blank."
            )
        }

        if(!email.matches(emailRegex.toRegex())) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "This is not a valid email."
            )
        }

        return ValidationResult(
            isSuccess = true,
            errorMessage = null
        )
    }
}