package com.example.bookreview.Network

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query


interface OpenLibraryApiService{
    @GET("search.json")
    suspend fun searchBooks(
    @Query("q") query: String,
    @Query("limit") limit: Int = 20,
    @Query("fields") fields: String = "key,title,author_name,cover_i,isbn,first_publish_year"
    ) :   OpenLibrarySearchResponse
}



