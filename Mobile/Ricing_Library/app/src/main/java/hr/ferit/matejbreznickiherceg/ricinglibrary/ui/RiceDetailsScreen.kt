package hr.ferit.matejbreznickiherceg.ricinglibrary.ui

import android.util.Log
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import hr.ferit.matejbreznickiherceg.ricinglibrary.CustomJsonParser
import hr.ferit.matejbreznickiherceg.ricinglibrary.NavigationController
import hr.ferit.matejbreznickiherceg.ricinglibrary.R
import hr.ferit.matejbreznickiherceg.ricinglibrary.Routes
import hr.ferit.matejbreznickiherceg.ricinglibrary.data.Comment
import hr.ferit.matejbreznickiherceg.ricinglibrary.data.Rice
import hr.ferit.matejbreznickiherceg.ricinglibrary.ui.theme.DarkOrange
import hr.ferit.matejbreznickiherceg.ricinglibrary.ui.theme.White
import java.util.concurrent.Executors

@Composable
fun RiceDetailsScreen (riceList: List<Rice>, navigation: NavController, riceId: Int, queue: RequestQueue) {
    val rice = riceList[riceId]
    var commentList by remember {
        mutableStateOf(ArrayList<Comment>())
    }
    val url = "http://192.168.107.3/orwima_project/get_comments_json.php?key=" + rice.externalId

    val commentRequest = StringRequest(
        Request.Method.GET, url,
        Response.Listener<String> { response ->
            val parser = CustomJsonParser()
            commentList = parser.parseJsonCommentData(response)
        },
        Response.ErrorListener {
            Log.e("commentRequest", "Request could not be accepted")
        }
    )

    queue.add(commentRequest)

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        TitleBar(title = "Ricing Library", iconResource = R.drawable.user)
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            IconButton(iconResource = R.drawable.ic_arrow_back) {
                navigation.popBackStack(Routes.SCREEN_ALL_RICES, false)
            }
        }
        TitleAndUsername(title = rice?.title, username = rice?.creator?.username)
        ImageGallery(rice?.image)
        CommentSection(comments = commentList)
    }
}

@Composable
fun IconButton(
    @DrawableRes iconResource: Int,
    size: Int = 40,
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
            .width(size.dp)
            .height(size.dp)
    ) {
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = null,
        )
    }
}

@Composable
fun TitleAndUsername(title: String?, username: String?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 40.dp, top = 10.dp)
    ) {
        Text(
            text = title ?: "No title",
            fontSize = 36.sp,
            color = DarkOrange,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = username ?: "No username",
            fontSize = 16.sp,
            color = DarkOrange,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
fun ImageGallery(image: ImageBitmap?) {
    Image(
        painter = BitmapPainter(image ?: ImageBitmap(1, 1)),
        contentDescription = "Image",
        modifier = Modifier
            .height(189.dp)
            .width(336.dp)
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = "Get dotfiles here",
            fontSize = 14.sp,
            color = DarkOrange,
            fontWeight = FontWeight.Light,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(end = 20.dp, top = 5.dp)
        )
    }
}

@Composable
fun Rating(average: Float) {
    Column {
        Row(modifier = Modifier.padding(start = 40.dp, top = 30.dp)) {
            Text(
                text = "Rating: $average",
                fontSize = 30.sp,
                color = DarkOrange,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Normal
            )
        }
        RatingButtons()
    }
}

@Composable
fun RatingButtons() {
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
        TabButton(text = "1", isActive = currentActiveButton == 0, width = 54)
        {
            currentActiveButton = 0
        }
        Spacer(Modifier.width(5.dp))
        TabButton(text = "2", isActive = currentActiveButton == 1, width = 54)
        {
            currentActiveButton = 1
        }
        Spacer(Modifier.width(5.dp))
        TabButton(text = "3", isActive = currentActiveButton == 2, width = 54)
        {
            currentActiveButton = 2
        }
        Spacer(Modifier.width(5.dp))
        TabButton(text = "4", isActive = currentActiveButton == 3, width = 54)
        {
            currentActiveButton = 3
        }
        Spacer(Modifier.width(5.dp))
        TabButton(text = "5", isActive = currentActiveButton == 4, width = 54)
        {
            currentActiveButton = 4
        }
    }
}

@Composable
fun CommentSection(comments: List<Comment>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 40.dp, top = 30.dp)
    ) {
        Row() {
            Text(
                text = "Comments:",
                fontSize = 30.sp,
                color = DarkOrange,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Normal
            )
        }
        LazyColumn() {
            items(comments.size) {
                Comment(username = comments[it].creator.username, text = comments[it].text)
            }
        }
    }
}

@Composable
fun Comment(username: String, text: String) {
    Column(
        modifier = Modifier
            .width(300.dp)
            .padding(vertical = 5.dp)
            .background(
                color = DarkOrange,
                shape = RoundedCornerShape(
                    topStart = 5.dp,
                    topEnd = 25.dp,
                    bottomEnd = 25.dp,
                    bottomStart = 25.dp
                )
            )
    ) {
        Text(
            text = "$username:",
            fontSize = 25.sp,
            color = White,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(horizontal = 5.dp)
        )
        Text(
            text = text,
            fontSize = 16.sp,
            color = White,
            fontWeight = FontWeight.Light,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
        )
    }
}