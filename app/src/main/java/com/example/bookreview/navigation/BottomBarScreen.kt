package com.example.bookreview.navigation

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.draw.InnerShadowScope
import androidx.navigation3.runtime.NavKey
import com.example.bookreview.R
import kotlinx.serialization.Serializable






val bottomBarItems = listOf<BottomBarScreen>(
    BottomBarScreen.Home,
    BottomBarScreen.Explore,
    BottomBarScreen.MyBooks,
    BottomBarScreen.Profile
)

@Serializable
sealed class BottomBarScreen(
    val icon: Int,
    val title: String
) : NavKey{
    @Serializable
    data object Home : BottomBarScreen(
        icon = R.drawable.home,
        title = "Home"
    )
    @Serializable
    data object Explore : BottomBarScreen(
        icon = R.drawable.search,
        title = "Explore"
    )
    @Serializable
    data object MyBooks : BottomBarScreen(
        icon = R.drawable.books,
        title = "My books"
    )
    @Serializable
    data object Profile : BottomBarScreen(
        icon = R.drawable.profile,
        title = "Profile"
    )



}


val BottomBarScreenSaver = Saver<BottomBarScreen, String>(
    save = {it::class.simpleName ?: "Unknown"} ,
    restore = {
        when(it){
            "Home" -> BottomBarScreen.Home
            "Profile" -> BottomBarScreen.Profile
            "Explore" -> BottomBarScreen.Explore
            "My books" -> BottomBarScreen.MyBooks

            else -> BottomBarScreen.Home
        }
    }


)