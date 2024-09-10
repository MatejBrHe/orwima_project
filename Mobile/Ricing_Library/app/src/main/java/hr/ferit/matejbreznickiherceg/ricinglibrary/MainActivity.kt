package hr.ferit.matejbreznickiherceg.ricinglibrary

import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import hr.ferit.matejbreznickiherceg.ricinglibrary.data.Comment
import hr.ferit.matejbreznickiherceg.ricinglibrary.data.Rice
import hr.ferit.matejbreznickiherceg.ricinglibrary.data.User
import org.json.JSONException
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors
import hr.ferit.matejbreznickiherceg.ricinglibrary.CustomJsonParser


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            val queue = Volley.newRequestQueue(this)
            val url = "http://192.168.107.3/orwima_project/get_rices_json.php"

            val riceRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    val parser = CustomJsonParser()
                    var executor = Executors.newSingleThreadExecutor()
                    executor.execute {
                        val riceList = parser.parseJsonRiceData(response)
                        setContent {
                            NavigationController(riceList, queue)
                        }
                    }
                },
                Response.ErrorListener {
                    Log.e("riceRequest", "Request could not be accepted")
                }
            )

            queue.add(riceRequest)
        }
    }
}