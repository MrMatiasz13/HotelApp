package pl.mrmatiasz.hotelapp.presentation.registration_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pl.mrmatiasz.hotelapp.domain.repository.AuthRepository
import pl.mrmatiasz.hotelapp.domain.use_case.validation.ValidateEmailUseCase
import pl.mrmatiasz.hotelapp.domain.use_case.validation.ValidatePasswordUseCase
import pl.mrmatiasz.hotelapp.domain.use_case.validation.ValidateRepeatPasswordUseCase
import pl.mrmatiasz.hotelapp.domain.use_case.validation.ValidateUsernameUseCase
import pl.mrmatiasz.hotelapp.util.Resource
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val validateUsernameUseCase: ValidateUsernameUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateRepeatPasswordUseCase: ValidateRepeatPasswordUseCase,

    private val authRepository: AuthRepository
): ViewModel() {
    var formState by mutableStateOf(RegistrationFormState())

    private var _registrationState = Channel<RegistrationState>()
    val registrationState = _registrationState.receiveAsFlow()

    fun onEvent(event: RegistrationEvent) {
        when(event) {
            is RegistrationEvent.UsernameChanged -> {
                formState = formState.copy(username = event.username)
                validateUsername()
            }

            is RegistrationEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
                validateEmail()
            }

            is RegistrationEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
                validatePassword()
            }

            is RegistrationEvent.RepeatedPasswordChanged -> {
                formState = formState.copy(repeatedPassword = event.repeatedPassword)
                validateRepeatedPassword()
            }

            is RegistrationEvent.Submit -> {
                val hasError = listOf(
                    validateUsername(),
                    validateEmail(),
                    validatePassword(),
                    validateRepeatedPassword()
                ).any { !it }

                if(!hasError) {
                    register(formState.email, formState.password)
                }
            }
        }
    }

    private fun validateUsername(): Boolean {
        val result = validateUsernameUseCase.execute(formState.username)
        formState = formState.copy(usernameError = result.errorMessage)
        return result.isSuccess
    }

    private fun validateEmail(): Boolean {
        val result = validateEmailUseCase.execute(formState.email)
        formState = formState.copy(emailError = result.errorMessage)
        return result.isSuccess
    }

    private fun validatePassword(): Boolean {
        val result = validatePasswordUseCase.execute(formState.password)
        formState = formState.copy(passwordError = result.errorMessage)
        return result.isSuccess
    }

    private fun validateRepeatedPassword(): Boolean {
        val result = validateRepeatPasswordUseCase.execute(formState.password, formState.repeatedPassword)
        formState = formState.copy(repeatedPasswordError = result.errorMessage)
        return result.isSuccess
    }

    private fun register(email: String, password: String) {
        viewModelScope.launch {
            authRepository.register(email, password).collect { result ->
                when(result) {
                    is Resource.Loading -> _registrationState.send(RegistrationState(isLoading = true))
                    is Resource.Success -> _registrationState.send(RegistrationState(isSuccess = "Sign up success."))
                    is Resource.Error -> {
                        _registrationState.send(RegistrationState(isError = result.message))
                        Log.d("SUPABASE_AUTH_ERROR", "${result.message}")
                    }
                }
            }
        }
    }
}