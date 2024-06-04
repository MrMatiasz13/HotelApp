package pl.mrmatiasz.hotelapp.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    errorMessage: String,
    isError: Boolean
) {
    Column(
        modifier = Modifier
            .padding(4.dp)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            placeholder = { Text(text = placeHolder) }
        )

        // error field
        if(isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .align(Alignment.End)
            )
        }
    }
}

@Composable
fun FormPasswordFiled(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    passwordVisibility: Boolean,
    onIconClick: () -> Unit,
    errorMessage: String,
    isError: Boolean
) {
    Column(
        modifier = Modifier
            .padding(4.dp)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            placeholder = { Text(text = placeHolder) },
            visualTransformation = if(!passwordVisibility) PasswordVisualTransformation()
                else VisualTransformation.None,
            trailingIcon = {
                val icon = if(!passwordVisibility) Icons.Default.VisibilityOff
                    else Icons.Default.Visibility

                IconButton(onClick = { onIconClick() }) {
                    Icon(imageVector = icon, contentDescription = "Visibility")
                }
            }
        )

        // error field
        if (isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .align(Alignment.End)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ComponentPreview() {

}