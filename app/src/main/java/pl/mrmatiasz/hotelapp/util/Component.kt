package pl.mrmatiasz.hotelapp.util

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FormTextField() {
    TextField(
        value = "",
        onValueChange = {}
    )
}

@Preview
@Composable
private fun ComponentPreview() {
    FormTextField()
}