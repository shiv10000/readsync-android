package com.example.bookreview.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    var number2 by mutableIntStateOf(0)

        private set

    fun incrementNumber2(){
        number2++
    }
    init {
        println("Search view model initialized")
    }
    override fun onCleared() {
        super.onCleared()
        println("Search view model cleared")
    }



}