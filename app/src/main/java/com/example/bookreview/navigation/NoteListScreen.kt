package com.example.bookreview.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp


@Composable
fun NoteListScreen(modifier: Modifier = Modifier,
                     onNoteClick: (Int) -> Unit,
                     ){

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {

        items(sampleNotes){
            note ->
            Column(
                modifier= Modifier.fillMaxWidth(
                )
                    .background(note.color)
                    .clickable{onNoteClick(note.int)}
            ) {
                Text(
                    text = note.title,
                    fontSize = 18. sp
                )
                Text(
                    text = note.content,
                )

            }


        }

    }

}