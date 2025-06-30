package com.example.bookreview.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Screen : NavKey{
    @Serializable
    data object Auth : Screen()
    @Serializable
    data object NestedGraph : Screen()
    @Serializable
    data object Setting : Screen()
}