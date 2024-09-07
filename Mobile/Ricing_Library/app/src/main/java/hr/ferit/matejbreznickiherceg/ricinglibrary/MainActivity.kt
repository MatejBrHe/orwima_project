package hr.ferit.matejbreznickiherceg.ricinglibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import hr.ferit.matejbreznickiherceg.ricinglibrary.data.Comment
import hr.ferit.matejbreznickiherceg.ricinglibrary.data.Rice
import hr.ferit.matejbreznickiherceg.ricinglibrary.data.User

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel: List<Rice> = listOf(
                Rice(
                    id = 1,
                    title = "rice1",
                    creator = User("Username"),
                    githubRepository = "none",
                    images = listOf(R.drawable.test, R.drawable.test),
                    rating = 4.7f,
                    comments = listOf(Comment(User("Username"), "Nice"), Comment(User("Username"), "Beautiful"), Comment(User("Username"), "It's OK")),
                    ),
                Rice(
                    id = 2,
                    title = "rice2",
                    creator = User("Username"),
                    githubRepository = "none",
                    images = listOf(R.drawable.test, R.drawable.test),
                    rating = 4.7f,
                    comments = listOf(Comment(User("Username"), "Nice"), Comment(User("Username"), "Beautiful"), Comment(User("Username"), "It's OK")),
                    ),
                Rice(
                    id = 3,
                    title = "rice3",
                    creator = User("Username"),
                    githubRepository = "none",
                    images = listOf(R.drawable.test, R.drawable.test),
                    rating = 4.7f,
                    comments = listOf(Comment(User("Username"), "Nice"), Comment(User("Username"), "Beautiful"), Comment(User("Username"), "It's OK")),
                    ),
                Rice(
                    id = 4,
                    title = "rice4",
                    creator = User("Username"),
                    githubRepository = "none",
                    images = listOf(R.drawable.test, R.drawable.test),
                    rating = 4.7f,
                    comments = listOf(Comment(User("Username"), "Nice"), Comment(User("Username"), "Beautiful"), Comment(User("Username"), "It's OK")),
                ),
            )

            setContent {
                NavigationController(viewModel)
            }
        }
    }
}