package pl.mrmatiasz.hotelapp.presentation.registration_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import pl.mrmatiasz.hotelapp.ui.theme.LightBlue
import pl.mrmatiasz.hotelapp.util.FormPasswordFiled
import pl.mrmatiasz.hotelapp.util.FormTextField

@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        TitleSection()

        Spacer(modifier = Modifier.height(16.dp))

        RegistrationFieldSection(viewModel)
    }
}

@Composable
fun TitleSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun RegistrationFieldSection(viewModel: RegistrationViewModel) {
    var passwordVisibility by remember { mutableStateOf(false) }

    val formState = viewModel.formState

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        FormTextField(
            value = formState.username,
            onValueChange = { viewModel.onEvent(RegistrationEvent.UsernameChanged(it)) },
            placeHolder = "Username",
            errorMessage = formState.usernameError.toString(),
            isError = formState.usernameError != null
        )

        FormTextField(
            value = formState.email,
            onValueChange = { viewModel.onEvent(RegistrationEvent.EmailChanged(it)) },
            placeHolder = "Email",
            errorMessage = formState.emailError.toString(),
            isError = formState.emailError != null
        )

        FormPasswordFiled(
            value = formState.password,
            onValueChange = { viewModel.onEvent(RegistrationEvent.PasswordChanged(it)) },
            placeHolder = "Password",
            passwordVisibility = passwordVisibility,
            onIconClick = { passwordVisibility = !passwordVisibility },
            errorMessage = formState.passwordError.toString(),
            isError = formState.passwordError != null
        )

        FormPasswordFiled(
            value = formState.repeatedPassword,
            onValueChange = { viewModel.onEvent(RegistrationEvent.RepeatedPasswordChanged(it)) },
            placeHolder = "Repeat Password",
            passwordVisibility = passwordVisibility,
            onIconClick = { passwordVisibility = !passwordVisibility },
            errorMessage = formState.repeatedPasswordError.toString(),
            isError = formState.repeatedPasswordError != null
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            colors = ButtonDefaults.buttonColors(LightBlue),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            onClick = { viewModel.onEvent(RegistrationEvent.Submit) }
        ) {
            Text(text = "Sign Up")
        }
    }
}