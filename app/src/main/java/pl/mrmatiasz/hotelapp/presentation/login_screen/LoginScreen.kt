package pl.mrmatiasz.hotelapp.presentation.login_screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import pl.mrmatiasz.hotelapp.presentation.navigation.RegistrationScreen
import pl.mrmatiasz.hotelapp.ui.theme.LightBlue
import pl.mrmatiasz.hotelapp.util.FormPasswordFiled
import pl.mrmatiasz.hotelapp.util.FormTextField

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Column (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ){
        TitleSection()

        Spacer(modifier = Modifier.height(16.dp))

        LoginFieldSection(viewModel)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Don't have account yet?")
            Text(
                text = "Register here",
                color = Color.Blue,
                modifier = Modifier.clickable {
                    navController.navigate(RegistrationScreen)
                }
            )
        }
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
            text = "Login",
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun LoginFieldSection(
    viewModel: LoginViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        var passwordVisibility by remember { mutableStateOf(false) }

        val formState = viewModel.formState

        val loginState = viewModel.loginState.collectAsState(initial = null)

        val context = LocalContext.current

        FormTextField(
            value = formState.email,
            onValueChange = { viewModel.onEvent(LoginEvent.EmailChanged(it)) },
            placeHolder = "Email",
            errorMessage = "${formState.emailError}",
            isError = formState.emailError != null
        )

        FormPasswordFiled(
            value = formState.password,
            onValueChange = { viewModel.onEvent(LoginEvent.PasswordChanged(it)) },
            placeHolder = "Password",
            passwordVisibility = passwordVisibility,
            onIconClick = { passwordVisibility = !passwordVisibility },
            errorMessage = "${formState.passwordError}",
            isError = formState.passwordError != null
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            colors = ButtonDefaults.buttonColors(LightBlue),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            onClick = { viewModel.onEvent(LoginEvent.Submit) }
        ) {
            Text(text = "Login")
        }

        LaunchedEffect(key1 = loginState.value?.isSuccess) {
            if(loginState.value?.isSuccess?.isNotEmpty() == true) {
                val successMessage = loginState.value?.isSuccess
                Toast.makeText(context, "$successMessage", Toast.LENGTH_SHORT).show()
            }
        }

        LaunchedEffect(key1 = loginState.value?.isError) {
            if(loginState.value?.isError?.isNotEmpty() == true) {
                val errorMessage = loginState.value?.isError
                Toast.makeText(context, "$errorMessage", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
