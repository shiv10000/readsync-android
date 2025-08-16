package com.example.bookreview.screens


 import android.annotation.SuppressLint
import androidx.collection.mutableObjectIntMapOf
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.approachLayout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookreview.AppFonts
 import com.example.bookreview.Network.BookDoc
 import com.example.bookreview.R
 import kotlinx.serialization.modules.serializersModuleOf
import java.nio.file.WatchEvent






@Composable
fun HomeScreen(modifier: Modifier = Modifier,
               libraryUistate : LibraryUiState
){
    when(libraryUistate){
        is LibraryUiState.Loading -> {

            LazyColumn {
                items(6) {
                    AnimatedBookShimmer()
                }
            }
        }
        is LibraryUiState.Success ->  bookList(messages = libraryUistate.data)
        is LibraryUiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Error loading books",
                        fontFamily = AppFonts.boldText
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { /* Add retry logic */ }) {
                        Text("Retry")
                    }
                }
    }}}
}


@Preview
@Composable

fun Home1() {

        val openlibraryViewModel: OpenlibraryViewModel = hiltViewModel()
        val libraryUiState by openlibraryViewModel.books.collectAsState()

    Column {
        bookscreenHeader(modifier = Modifier.padding(top = 50.dp))
        BookTab()
        HomeScreen(libraryUistate = libraryUiState)
    }

    }










@Preview
@Composable
fun bookscreenHeader(modifier: Modifier = Modifier){

        Row(modifier= modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically


    ) {

        Text(
            text = "My Books",
            fontFamily = AppFonts.boldText,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
        )
    }

}

@Composable
fun bookReadMenu(modifier: Modifier = Modifier, menuName : String, inputfullWidth : Dp,isSelected: Boolean, onClick : () -> Unit){
    var fullWidth = inputfullWidth
    val animatedLineWidth by animateDpAsState(
        targetValue = if (isSelected) fullWidth else 0.dp,
        label = "lineWidthAnimation"
    )
    Row(
        horizontalArrangement = Arrangement.SpaceBetween


    ) {
        Column(
             modifier= Modifier.clickable(onClick = onClick ),
             horizontalAlignment = Alignment.Start
         ) {

            Text(
                modifier = Modifier.size(width = fullWidth, height = 21.dp),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                text = menuName,
                fontFamily = AppFonts.boldText,
             )
            Box(
                modifier = Modifier.height(3.dp).width(animatedLineWidth).background(Color.Green)
            )

        }
    }
}
@Preview
@Composable
fun BookTab(modifier: Modifier = Modifier){

    var selectTabIndex by remember {mutableStateOf(0)}
    val tabList = listOf("Want to Read","Currently Reading", "Read")

    val textMeasurer = rememberTextMeasurer()
    // 2. Define the style that will be used for the text. It must match the Text composable.
     // 3. Get the current screen density to convert pixels to Dp.
    val density = LocalDensity.current

    // 4. Remember the calculated widths. This ensures the calculation only runs when the
    // tab list changes, not on every recomposition.
    val tabWidths = remember(tabList) {
        tabList.map { tabText ->
             val textWidthInPixels = textMeasurer.measure(tabText).size.width
             with(density) { textWidthInPixels.toDp() + 24.dp }
        }
    }
    Column(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp)

     ) {
        Row(modifier= Modifier.padding(vertical = 20.dp),

            horizontalArrangement = Arrangement.spacedBy(32.dp)
        )
        {
            tabList.forEachIndexed {
                    index,text -> bookReadMenu(
                menuName = text,
                isSelected = selectTabIndex == index,
                onClick = {selectTabIndex = index},
                inputfullWidth =tabWidths[index])
            }
        }

    }
    }

@Composable
fun bookList(
    messages: List<BookDoc>
) {

    LazyColumn {
        items(messages) { message ->
            bookscreen(item = message.title.toString())
        }
    }
}

@Composable
fun bookscreen(modifier: Modifier = Modifier, item : String){
    Row(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Column(
            modifier = Modifier.weight(1f).padding(end = 16.dp)
        ) {
            Text(
                text = "In Progress",
                fontFamily = AppFonts.boldText,
                fontSize = 14.sp
            )
            Text(
                text = item,
                fontFamily = AppFonts.boldText,
                fontSize = 16.sp


            )
            Text(
                text = "Alex Michaelides",
                fontFamily = AppFonts.boldText,
                fontSize = 14.sp

            )
            Spacer(
                modifier = Modifier.padding(top= 16.dp)
            )
            Box(
                modifier.size(width = 84.dp, height = 32.dp).background(colorResource(R.color.grey),
                        shape = RoundedCornerShape(8.dp)
                )
            ){
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                ){
                    Text(
                        text = "60%",
                        fontFamily = AppFonts.boldText,
                        modifier = Modifier.padding(end = 4.dp)
                    )

                    Text(
                        text = ">",
                        fontFamily = AppFonts.boldText,
                    )
                }
            }
        }
        Box(
            modifier.size(
                width = 133.dp,
                height = 118.dp
            ).clip(RoundedCornerShape(16.dp))
                .background(colorResource(R.color.pinkapp))
            )
    }
}


























