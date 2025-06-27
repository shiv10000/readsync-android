package com.example.bookreview.Network

import OpenLibrarySearchResponse
import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.annotation.processing.Generated

private const val BASE_URL =
    "https://openlibrary.org/"
private val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
}
private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface OpenLibraryApiService{
    @GET("search.json")
    suspend fun searchBooks(
    @Query("q") query: String,
    @Query("limit") limit: Int = 20,
    @Query("fields") fields: String = "key,title,author_name,cover_i,isbn,first_publish_year"
    ) :   OpenLibrarySearchResponse
}
object OpenLibraryApi {
    val retrofitService : OpenLibraryApiService by lazy {
        Log.d("MarsApi", "Creating Retrofit service with BASE_URL: $BASE_URL")
        retrofit.create(OpenLibraryApiService::class.java)
    }
}


