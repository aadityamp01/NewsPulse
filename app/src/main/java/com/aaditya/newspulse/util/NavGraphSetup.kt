package com.aaditya.newspulse.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aaditya.newspulse.ui.article_screen.ArticleScreen
import com.aaditya.newspulse.ui.news_screen.NewsScreen
import com.aaditya.newspulse.ui.news_screen.NewsScreenViewModel
import com.aaditya.newspulse.ui.splash_screen.SplashScreen

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraphSetup(
    navController: NavHostController
) {
    val argKey = "web_url"

    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable(route = "splash_screen") {
            SplashScreen(navController = navController)
        }
        composable(route = "news_screen") {
            val viewModel: NewsScreenViewModel = hiltViewModel()
            NewsScreen(
                state = viewModel.state,
                onEvent = viewModel::onEvent,
                onReadFullStoryButtonClick = { url ->
                    navController.navigate("article_screen?$argKey=$url")
                }
            )
        }
        composable(
            route = "article_screen?$argKey={$argKey}",
            arguments = listOf(navArgument(name = argKey) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            ArticleScreen(
                url = backStackEntry.arguments?.getString(argKey),
                onBackPressed = { navController.navigateUp() }
            )
        }
    }
}