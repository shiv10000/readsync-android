package com.example.bookreview.navigation

 import android.R
  import androidx.compose.ui.graphics.Color
 import kotlin.random.Random

data class Note(
    val int : Int,
    val title : String,
    val content : String,
    val color : Color
)

val sampleNotes = List(100){
    Note(
        it,
        "Note $it",
        "Content $it",
        color = Color(Random.nextLong(0xFFFFFFFF)) .copy(alpha = 0.5f)
    )

}