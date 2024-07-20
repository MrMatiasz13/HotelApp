package pl.mrmatiasz.hotelapp.presentation.login_screen

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
import pl.mrmatiasz.hotelapp.util.Resource
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase

    private val authRepository: AuthRepository
): ViewModel() {
    var formState by mutableStateOf(LoginFormState())

    private var _loginState = Channel<LoginState>()
    val loginState = _loginState.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
                validateEmail()
            }

            is LoginEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
                validatePassword()
            }

            is LoginEvent.Submit -> {
                val errorList = listOf(
                    validateEmail(),
                    validatePassword()
                )

                if(!errorList.any()) {
                    Log.d("SUB_TEST", "There is an error")
                }

                else {

                }
            }
        }
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

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.register(email, password).collect { result ->
                when(result) {
                    is Resource.Loading -> _loginState.send(LoginState(isLoading = true))
                    is Resource.Success -> _loginState.send(LoginState(isSuccess = "Sign in success."))
                    is Resource.Error -> {
                        _loginState.send(LoginState(isError = result.message))
                        Log.d("SUPABASE_AUTH_ERROR", "${result.message}")
                    }
                }
            }
        }
    }
}
