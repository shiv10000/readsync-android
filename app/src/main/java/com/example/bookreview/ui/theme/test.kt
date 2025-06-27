package com.example.bookreview.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Preview
@Composable
fun Home(modifier: Modifier = Modifier) {


    Column(
        modifier = Modifier.size(200.dp)
    ) {
        Row {
            Text("Hello",
                modifier = Modifier.background(Color.Green).padding(20.dp).background(Color.Red)
            )
            Text("Hello",
                modifier = Modifier.background(Color.Green).padding(20.dp).background(Color.Red)
            )
            Text("Hello",
                modifier = Modifier.background(Color.Green).padding(20.dp).background(Color.Red)
            )
        }


        Text("Worldsjsijjkjwjemmmmmmmmmmmmm ")
        Text("Worldsjsijjkjwjejejdjdjdjjdjdjdjjkjkjjjkjj")
        Text("Worldsjsijjkjwjejejdjdjdjjdjdjdjjkjkjjjkjj")
        Text("Worldsjsijjkjwjejejdjdjdjjdjdjdjjkjkjjjkjj")
        Text("Worldsjsijjkjwjejejdjdjdjjdjdjdjjkjkjjjkjj")


    }





}