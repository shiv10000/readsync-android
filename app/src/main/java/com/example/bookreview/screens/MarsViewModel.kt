package com.example.bookreview.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookreview.data.BooksRepository
import com.example.bookreview.Network.BookDoc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
@HiltViewModel
class OpenlibraryViewModel @Inject constructor( private val booKRepository: BooksRepository) : ViewModel() {

    // Private mutable state


        companion object {
            private const val TAG = "OpenlibraryViewModel"
        }

        // Private mutable state
        private val _books = MutableStateFlow<LibraryUiState>(LibraryUiState.Loading)

        // Public read-only state
        val books: StateFlow<LibraryUiState> = _books.asStateFlow()

        init {
            searchBooks("harry potter")
        }



        // Function to search with custom query
        fun searchBooks(query: String) {
            Log.d(TAG, "searchBooks() called with query: '$query'")
            viewModelScope.launch {
                Log.d(TAG, "Starting coroutine for searchBooks with query: '$query'")
                _books.value = LibraryUiState.Loading
                Log.d(TAG, "State set to Loading")

                try {
                    Log.d(TAG, "Making API call to search for '$query'")
                    val listResult = booKRepository.searchforBooks(query)

                    if (listResult.docs.isNotEmpty()) {
                        Log.d(TAG, "First book title: ${listResult.docs[0].title}")
                        Log.d(TAG, "First book authors: ${listResult.docs[0].authorName}")
                    }

                    _books.value = LibraryUiState.Success(listResult.docs)
                    Log.d(TAG, "State set to Success with ${listResult.docs.size} books")

                } catch (e: Exception) {
                    _books.value = LibraryUiState.Error
                    Log.d(TAG, "State set to Error")
                }
            }
        }
    }

    sealed interface LibraryUiState {
        data class Success(val data: List<BookDoc>) : LibraryUiState
        object Loading : LibraryUiState
        object Error : LibraryUiState
    }