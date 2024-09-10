package hr.ferit.matejbreznickiherceg.ricinglibrary

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import hr.ferit.matejbreznickiherceg.ricinglibrary.data.Comment
import hr.ferit.matejbreznickiherceg.ricinglibrary.data.Rice
import hr.ferit.matejbreznickiherceg.ricinglibrary.data.User
import org.json.JSONException
import org.json.JSONObject
import java.net.URL
import java.util.concurrent.Executors

fun getImgFromURL(imageURL: String): ImageBitmap{
    val url = URL(imageURL).openStream()
    val img = BitmapFactory.decodeStream(url).asImageBitmap()
    return img
}

class CustomJsonParser {
    fun parseJsonRiceData(response: String): ArrayList<Rice> {
        val riceList = ArrayList<Rice>()
        try {
            val jsonObject = JSONObject(response)
            val jsonArray = jsonObject.getJSONArray("rices")
            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                var img = getImgFromURL("http://192.168.107.3/orwima_project/" + obj.getString("img_path"))
                val newRice = Rice(
                    internalId = i,
                    externalId = obj.getString("id"),
                    title = obj.getString("title"),
                    creator = User(obj.getString("createdBy")),
                    githubRepository = obj.getString("repo"),
                    image = img,
                )
                riceList.add(newRice)
            }
        }
        catch(e: JSONException){
            Log.e("parseJsonData", "unexpected JSON exception", e)
            return  ArrayList<Rice>()
        }
        return riceList
    }

    fun parseJsonCommentData(response: String): ArrayList<Comment> {
        val commentList = ArrayList<Comment>()
        try {
            val jsonObject = JSONObject(response)
            val jsonArray = jsonObject.getJSONArray("comments")
            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                val newComment = Comment(
                    creator = User(username = obj.getString("createdBy")),
                    text = obj.getString("text")
                )
                commentList.add(newComment)
            }
        }
        catch(e: JSONException){
            Log.e("parseJsonData", "unexpected JSON exception", e)
            return  ArrayList<Comment>()
        }
        return commentList
    }
}