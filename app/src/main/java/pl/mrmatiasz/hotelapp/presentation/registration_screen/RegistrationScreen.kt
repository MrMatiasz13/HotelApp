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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pl.mrmatiasz.hotelapp.ui.theme.LightBlue
import pl.mrmatiasz.hotelapp.util.FormPasswordFiled
import pl.mrmatiasz.hotelapp.util.FormTextField

@Composable
fun RegistrationScreen(
    navController: NavController?
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        TitleSection()

        Spacer(modifier = Modifier.height(16.dp))

        RegistrationFieldSection()
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
fun RegistrationFieldSection() {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        FormTextField(
            value = username,
            onValueChange = { username = it },
            placeHolder = "Username",
            errorMessage = "test error",
            isError = isError
        )

        FormTextField(
            value = email,
            onValueChange = { email = it },
            placeHolder = "Email",
            errorMessage = "test Error",
            isError = isError
        )

        FormPasswordFiled(
            value = password,
            onValueChange = { password = it },
            placeHolder = "Password",
            passwordVisibility = passwordVisibility,
            onIconClick = { passwordVisibility = !passwordVisibility },
            errorMessage = "test",
            isError = isError
        )

        FormPasswordFiled(
            value = repeatPassword,
            onValueChange = { repeatPassword = it },
            placeHolder = "Repeat Password",
            passwordVisibility = passwordVisibility,
            onIconClick = { passwordVisibility = !passwordVisibility },
            errorMessage = "test",
            isError = isError
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            colors = ButtonDefaults.buttonColors(LightBlue),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Sign Up")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegistrationScreenPreview() {
    RegistrationScreen(navController = null)
}