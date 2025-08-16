package com.example.bookreview.data

import com.example.bookreview.Network.OpenLibrarySearchResponse
import com.example.bookreview.Network.OpenLibraryApiService
import javax.inject.Inject
import javax.inject.Singleton

interface BooksRepository {
    suspend fun searchforBooks(query : String) : OpenLibrarySearchResponse
}

class BooksRepositoryImpl @Inject constructor(
    private val api: OpenLibraryApiService
) : BooksRepository {
    override suspend fun searchforBooks(query: String): OpenLibrarySearchResponse {
        return api.searchBooks(query)
    }
}
