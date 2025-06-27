package com.example.bookreview

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun TextWithPaddingPreview() {
    Text(
        text = "Hello, Compose!",
        modifier = Modifier
            .padding(16.dp)
            .background(Color.LightGray)
            .border(1.dp, Color.Red)
    )
}