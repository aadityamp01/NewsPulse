package com.aaditya.newspulse

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.aaditya.newspulse.ui.news_screen.NewsScreen
import com.aaditya.newspulse.ui.news_screen.NewsScreenViewModel
import com.aaditya.newspulse.ui.theme.NewsPulseTheme
import com.aaditya.newspulse.util.NavGraphSetup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsPulseTheme {
//                val viewModel: NewsScreenViewModel = hiltViewModel()
//                NewsScreen(state = viewModel.state, onEvent = viewModel::onEvent)
                val navController = rememberNavController()
                NavGraphSetup(navController = navController)
            }
        }
    }
}
