package hr.ferit.matejbreznickiherceg.ricinglibrary.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.ferit.matejbreznickiherceg.ricinglibrary.R
import hr.ferit.matejbreznickiherceg.ricinglibrary.ui.theme.DarkOrange
import hr.ferit.matejbreznickiherceg.ricinglibrary.ui.theme.Silver
import hr.ferit.matejbreznickiherceg.ricinglibrary.ui.theme.White

@Preview(showBackground = true)
@Composable
fun AddRiceScreen () {
    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        TitleBar(title = "Add New Rice")
        Row (
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            IconButton(iconResource = R.drawable.ic_arrow_back)
        }
        TextInput(labelText = "Title")
        TextInput(labelText = "Github repository")
        AddImage()
        TextButton(text = "Upload")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(
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
    var searchInput by remember {
        mutableStateOf("")
    }
    TextField(
        value = searchInput,
        onValueChange = { searchInput = it },
        label = {
            Text(labelText)
        },
        colors = colors,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 15.dp)
            .border(1.dp, Silver, shape = RoundedCornerShape(25.dp))
    )
}

@Composable
fun AddImage() {
    Box(modifier = Modifier
        .height(300.dp)
        .width(300.dp)) {
        Image(
            painter = painterResource(id = R.drawable.img_icon),
            contentDescription = "Image",
            modifier = Modifier.fillMaxSize()
            )
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 200.dp),
            horizontalArrangement = Arrangement.Center
            ) {
            Text(
                text = "+ Add images",
                color = White,
                fontSize = 30.sp
            )
        }
    }
}

@Composable
fun TextButton(
    text: String,
    height: Int = 50,
    width: Int = 100,
    color: Color = DarkOrange,
    onClick: () -> Unit = {}
) {
    Button(
        contentPadding = PaddingValues(),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 12.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = White, contentColor = color),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .border(width = 2.dp, color = DarkOrange, shape = RoundedCornerShape(15.dp))
            .width(width.dp)
            .height(height.dp)
    ) {
        Text(text = text)
    }
}