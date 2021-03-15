package com.example.androiddevchallenge

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MySootheButton(text: String, onClick: () -> Unit = {}, background: Color = MaterialTheme.colors.primary) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(72.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(backgroundColor = background)
    ) {
        Text(text = text.toUpperCase(), style = MaterialTheme.typography.button)
    }
}