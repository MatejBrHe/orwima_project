package hr.ferit.matejbreznickiherceg.ricinglibrary

import androidx.compose.runtime.Composable
import hr.ferit.matejbreznickiherceg.ricinglibrary.data.Rice
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hr.ferit.matejbreznickiherceg.ricinglibrary.ui.MainScreen
import hr.ferit.matejbreznickiherceg.ricinglibrary.ui.RiceDetailsScreen

object Routes {
    const val SCREEN_ALL_RICES = "riceList"
    const val SCREEN_RICE_DETAILS = "riceDetails/{riceId}"

    fun getRiceDetailsPath(riceId: Int?) : String {
        if (riceId != null && riceId != -1) {
            return "riceDetails/$riceId"
        }
        return "riceDetails/0"
    }
}

@Composable
fun NavigationController( viewModel: List<Rice> ) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =
    Routes.SCREEN_ALL_RICES) {
        composable(Routes.SCREEN_ALL_RICES) {
            MainScreen(
                viewModel = viewModel,
                navigation = navController
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
                RiceDetailsScreen(viewModel = viewModel, navigation = navController, riceId = it)
            }
        }
    }
}