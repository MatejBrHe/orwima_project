package hr.ferit.matejbreznickiherceg.ricinglibrary.ui

import android.util.Log
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import hr.ferit.matejbreznickiherceg.ricinglibrary.CustomJsonParser
import hr.ferit.matejbreznickiherceg.ricinglibrary.NavigationController
import hr.ferit.matejbreznickiherceg.ricinglibrary.R
import hr.ferit.matejbreznickiherceg.ricinglibrary.RiceViewModel
import hr.ferit.matejbreznickiherceg.ricinglibrary.Routes
import hr.ferit.matejbreznickiherceg.ricinglibrary.data.Rice
import hr.ferit.matejbreznickiherceg.ricinglibrary.ui.theme.DarkOrange
import hr.ferit.matejbreznickiherceg.ricinglibrary.ui.theme.Silver
import hr.ferit.matejbreznickiherceg.ricinglibrary.ui.theme.White
import hr.ferit.matejbreznickiherceg.ricinglibrary.ui.theme.Whitesmoke
import java.util.concurrent.Executors

@Composable
fun MainScreen(riceList: List<Rice>, navigation: NavController, queue: RequestQueue) {
    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        TitleBar(title = "Ricing Library")

        val state = remember {
            mutableStateOf(TextFieldValue(""))
        }

        SearchBar(state = state, iconResource = R.drawable.ic_search, labelText = "Search")

        var list = riceList
        if(state.value.text != ""){
            list = riceList.filter { it.title.contains(state.value.text) }
        }
        RiceList(rices = list, navigation = navigation)
    }
}

@Composable
fun TitleBar(title: String) {
    Row (
        modifier = Modifier
            .height(60.dp)
            .background(color = DarkOrange)
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = TextStyle(
                color = White,
                fontSize = 28.sp,
                fontWeight = FontWeight.W400,
                fontStyle = FontStyle.Italic
            ),
            modifier = Modifier.width(335.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    state: MutableState<TextFieldValue>,
    @DrawableRes iconResource: Int,
    labelText: String,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        containerColor = Color.Transparent,
        placeholderColor = Silver,
        textColor = Silver,
        unfocusedLabelColor = Silver,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )
) {
    TextField(
        value = state.value,
        onValueChange = { state.value = it },
        label = {
            Text(labelText)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = labelText,
                tint = Silver,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
            )
        },
        colors = colors,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 15.dp)
            .border(1.dp, Silver, shape = RoundedCornerShape(25.dp))
    )
}

@Composable
fun TabButton(
    text: String,
    isActive: Boolean,
    width: Int,
    onClick: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(25.dp),
        elevation = null,
        colors = if (isActive) ButtonDefaults.buttonColors(contentColor = White, containerColor = DarkOrange)
        else ButtonDefaults.buttonColors(contentColor = Silver, containerColor = Whitesmoke),
        modifier = Modifier
            .fillMaxHeight()
            .width(width.dp),
        onClick = { onClick() }
    ) {
        Text(text)
    }
}

@Composable
fun RiceSortingButtons() {
    var currentActiveButton by remember { mutableStateOf(0) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .background(Color.Transparent)
            .fillMaxWidth()
            .height(44.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        TabButton(
            text = "All",
            isActive = currentActiveButton == 0,
            width = 100
        ) {
            currentActiveButton = 0
        }
        Spacer(Modifier.width(5.dp))
        TabButton(
            text = "New",
            isActive = currentActiveButton == 1,
            width = 100
        ) {
            currentActiveButton = 1
        }
        Spacer(Modifier.width(5.dp))
        TabButton(
            text = "Popular",
            isActive = currentActiveButton == 2,
            width = 100
        ) {
            currentActiveButton = 2
        }
    }
}

@Composable
fun RiceCard(
    title: String,
    imageResource: ImageBitmap,
    onClick: () -> Unit = {}
) {
    Card (
        modifier = Modifier
            .height(189.dp)
            .width(336.dp)
            .background(color = Color.Transparent)
            .padding(vertical = 5.dp)
            .clickable {
                onClick()
            }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = BitmapPainter(imageResource),
                contentDescription = title,
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = title,
                modifier = Modifier
                    .padding(top = 144.dp)
                    .background(color = Color.Black)
                    .fillMaxWidth(),
                style = TextStyle(
                    color = White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            )
        }
    }
}

@Composable
fun RiceList(rices: List<Rice>, navigation: NavController)
{
    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(vertical = 20.dp)
    ) {
        items(rices.size) {
            RiceCard(rices[it].title,  rices[it].image) {
                navigation.navigate(Routes.getRiceDetailsPath(rices[it].internalId))
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

