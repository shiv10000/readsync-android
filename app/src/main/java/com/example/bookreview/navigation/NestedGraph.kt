package com.example.bookreview.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.bookreview.R
import com.example.bookreview.screens.Home1
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NestedGraph(navigateToSetting : () -> Unit){

    val backStack = rememberNavBackStack<BottomBarScreen>(BottomBarScreen.Home)
    var currentBottomBarScreen : BottomBarScreen by rememberSaveable(
        stateSaver =  BottomBarScreenSaver
    ) {
        mutableStateOf(BottomBarScreen.Home)
}

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Book Review")},
                actions = {
                    IconButton(onClick = navigateToSetting) {
                        Icon(
                            painter =  painterResource(id = R.drawable.books),
                            contentDescription = null
                        )
                    }
                }

            )
        },
        bottomBar ={
            NavigationBar(
                containerColor = Color.White

            ) {
                bottomBarItems.forEach {
                    destination ->NavigationBarItem(
                        selected = currentBottomBarScreen == destination,
                         icon = {Icon(
                             modifier = Modifier.size(height = 30.dp, width = 30.dp),
                             painter =  painterResource(destination.icon),
                             contentDescription = null
                         ) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        selectedTextColor = Color.Black,
                        indicatorColor = Color.Transparent,
                        unselectedIconColor = colorResource(id = R.color.themeyellow),
                        unselectedTextColor = colorResource(id = R.color.themeyellow),
                        disabledIconColor = Color.Gray,
                        disabledTextColor = Color.Gray
                    ),
                    label = {Text(destination.title)},
                        onClick =  {
                            if(backStack.lastOrNull() != destination){   //This is used to chack we are adding multiple screens or not in the backstack
                                    //This
                                    backStack.removeAt(backStack.lastIndex)

                                backStack.add(destination)
                                currentBottomBarScreen =  destination
                            }

                        }
                    )
                }
            }
        }


    ) {

        NavDisplay(
            backStack=backStack,
            onBack = {backStack.removeLastOrNull()},
            entryDecorators = listOf(
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<BottomBarScreen.Home>{

                    Home1()

                }
                entry<BottomBarScreen.Profile>{
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Text("Profile")
                    }


                }
                entry<BottomBarScreen.MyBooks>{
                    var number by rememberSaveable { mutableIntStateOf(0)  }
                    val viewModel = viewModel<SearchViewModel>()
                    val number2 = viewModel.number2


                    LaunchedEffect(Unit) {
                        while (true){
                            delay(2000)
                            number++
                            viewModel.incrementNumber2()

                        }
                    }

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Text("Search -  ($number - $number2)")
                    }

                }

                entry<BottomBarScreen.Explore>{
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Text("Explore")
                    }

                }


            }

        )

    }


}