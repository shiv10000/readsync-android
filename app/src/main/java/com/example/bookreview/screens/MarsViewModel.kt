import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookreview.Network.OpenLibraryApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class OpenlibraryViewModel : ViewModel() {

    // Private mutable state


        companion object {
            private const val TAG = "OpenlibraryViewModel"
        }

        // Private mutable state
        private val _books = MutableStateFlow<LibraryUiState>(LibraryUiState.Loading)

        // Public read-only state
        val books: StateFlow<LibraryUiState> = _books.asStateFlow()

        init {
            Log.d(TAG, "ViewModel initialized")
            getBooks()
        }

        fun getBooks() {
            Log.d(TAG, "getBooks() called")
            viewModelScope.launch {
                Log.d(TAG, "Starting coroutine for getBooks")
                _books.value = LibraryUiState.Loading
                Log.d(TAG, "State set to Loading")

                try {
                    Log.d(TAG, "Making API call to search for 'harry potter'")
                    val listResult = OpenLibraryApi.retrofitService.searchBooks("Harry potter")

                    Log.d(TAG, "API call successful!")
                    Log.d(TAG, "Response received - numFound: ${listResult.numFound}")
                    Log.d(TAG, "Number of books in docs: ${listResult.docs.size}")

                    if (listResult.docs.isNotEmpty()) {
                        Log.d(TAG, "First book title: ${listResult.docs[0].title}")
                        Log.d(TAG, "First book authors: ${listResult.docs[0].authorName}")
                        Log.d(TAG, "First book cover ID: ${listResult.docs[0].coverId}")
                    }

                    _books.value = LibraryUiState.Success(listResult.docs)
                    Log.d(TAG, "State set to Success with ${listResult.docs.size} books")

                } catch (e: Exception) {
                    Log.e(TAG, "API call failed with exception: ${e.javaClass.simpleName}")
                    Log.e(TAG, "Exception message: ${e.message}")
                    Log.e(TAG, "Exception stack trace:", e)
                    _books.value = LibraryUiState.Error
                    Log.d(TAG, "State set to Error")
                }
            }
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
                    val listResult = OpenLibraryApi.retrofitService.searchBooks(query)

                    Log.d(TAG, "API call successful for query '$query'!")
                    Log.d(TAG, "Response received - numFound: ${listResult.numFound}")
                    Log.d(TAG, "Number of books in docs: ${listResult.docs.size}")

                    if (listResult.docs.isNotEmpty()) {
                        Log.d(TAG, "First book title: ${listResult.docs[0].title}")
                        Log.d(TAG, "First book authors: ${listResult.docs[0].authorName}")
                    }

                    _books.value = LibraryUiState.Success(listResult.docs)
                    Log.d(TAG, "State set to Success with ${listResult.docs.size} books")

                } catch (e: Exception) {
                    Log.e(TAG, "API call failed for query '$query' with exception: ${e.javaClass.simpleName}")
                    Log.e(TAG, "Exception message: ${e.message}")
                    Log.e(TAG, "Exception stack trace:", e)
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