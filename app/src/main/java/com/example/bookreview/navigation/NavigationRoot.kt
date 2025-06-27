package com.example.bookreview.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import kotlinx.serialization.Serializable

@Serializable
data object NoteListScreenUI :  NavKey
@Serializable

data class NoteDetailScreenUI(val Id : Int) : NavKey

@Composable
fun NavigationRoot(modifier: Modifier= Modifier){



    val backStack = rememberNavBackStack(NoteListScreenUI,NoteDetailScreenUI(8),NoteDetailScreenUI(99))
    NavDisplay(
        backStack = backStack,
        entryProvider ={
             key ->
            when(key){
                is NoteListScreenUI -> {
                    NavEntry(key= key){
                        NoteListScreen(
                            onNoteClick = {
                                noteId -> backStack.add(NoteDetailScreenUI(noteId))
                            }
                        )

                    }
                }
                is NoteDetailScreenUI -> {
                    NavEntry(key= key){
                        NoteDetailScreen(key.Id)
                    }
                }
                else -> {
                    throw RuntimeException("Invalid route")
                }
            }
        }


    )
}