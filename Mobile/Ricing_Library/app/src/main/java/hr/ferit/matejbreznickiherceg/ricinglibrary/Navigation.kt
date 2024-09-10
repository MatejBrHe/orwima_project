package hr.ferit.matejbreznickiherceg.ricinglibrary

import android.util.Log
import androidx.compose.runtime.Composable
import hr.ferit.matejbreznickiherceg.ricinglibrary.data.Rice
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import hr.ferit.matejbreznickiherceg.ricinglibrary.data.Comment
import hr.ferit.matejbreznickiherceg.ricinglibrary.ui.MainScreen
import hr.ferit.matejbreznickiherceg.ricinglibrary.ui.RiceDetailsScreen
import java.util.concurrent.Executors

object Routes {
    const val SCREEN_ALL_RICES = "riceList"
    const val SCREEN_RICE_DETAILS = "riceDetails/{riceId}"

    fun getRiceDetailsPath(riceId: Int?) : String {
        if (riceId != null && riceId != -1) {
            return "riceDetails/$riceId"
            Log.e("Here", "$riceId")
        }
        return "riceDetails/0"
    }
}

@Composable
fun NavigationController( riceList: List<Rice>, queue: RequestQueue) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =
    Routes.SCREEN_ALL_RICES) {
        composable(Routes.SCREEN_ALL_RICES) {
            MainScreen(
                riceList = riceList,
                navigation = navController,
                queue = queue
            )
        }
        composable(
            Routes.SCREEN_RICE_DETAILS,
            arguments = listOf(
                navArgument("riceId") {
                    type = NavType.IntType
                }
            )
        ) {backStackEntry ->
            backStackEntry.arguments?.getInt("riceId")?.let {
                RiceDetailsScreen(riceList = riceList, navigation = navController, riceId = it, queue = queue)
            }
        }
    }
}