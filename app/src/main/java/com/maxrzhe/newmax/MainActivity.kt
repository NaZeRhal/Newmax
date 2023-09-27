package com.maxrzhe.newmax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.maxrzhe.newmax.navigation.Destination
import com.maxrzhe.newmax.navigation.LocalNavController
import com.maxrzhe.newmax.navigation.ROOT_GRAPH
import com.maxrzhe.newmax.navigation.TOPICS_SELECTION_GRAPH
import com.maxrzhe.newmax.navigation.homeNavGraph
import com.maxrzhe.newmax.navigation.topicsSelectionNavGraph
import com.maxrzhe.newmax.screens.bottom_bar.AppBottomBar
import com.maxrzhe.newmax.screens.header.AppTopBar
import com.maxrzhe.newmax.theme.NewmaxTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI

class MainActivity : ComponentActivity() {

  private val isSplashShowing = MutableStateFlow(true)

  @OptIn(KoinExperimentalAPI::class, ExperimentalLayoutApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    WindowCompat.setDecorFitsSystemWindows(window, false)

    lifecycleScope.launch {
      delay(2000)
      isSplashShowing.update { false }
    }

    val splashScreen = installSplashScreen()
    splashScreen.setKeepOnScreenCondition {
      isSplashShowing.value
    }

    setContent {
      val navController = rememberNavController()
      val currentBackStackEntry by navController.currentBackStackEntryAsState()
      var showTopBar by remember { mutableStateOf(false) }
      var showBottomBar by remember { mutableStateOf(false) }
      var destination by remember { mutableStateOf<Destination?>(null) }

      LaunchedEffect(currentBackStackEntry) {
        val route = currentBackStackEntry?.destination?.route
        destination = Destination.fromRoute(route)
        showTopBar = destination != Destination.Details
        showBottomBar = destination != Destination.Topics && destination != Destination.Details
      }

      CompositionLocalProvider(LocalNavController provides navController) {
        KoinAndroidContext {
          NewmaxTheme {
            Scaffold(
              modifier = Modifier.fillMaxSize(),
              topBar = {
                AppTopBar(
                  destination = destination,
                  onBackPressed = {
                    navController.popBackStack()
                  }
                )
              },
              bottomBar = {
                if (showBottomBar) {
                  AppBottomBar()
                }
              }
            ) { paddings ->
              Column(
                modifier = Modifier.padding(paddings)
//                  .border(width = 1.dp, color = Color.Black)
              ) {
                NavHost(
                  navController = navController,
                  startDestination = TOPICS_SELECTION_GRAPH,
                  route = ROOT_GRAPH
                ) {
                  topicsSelectionNavGraph()
                  homeNavGraph()
                }
              }
            }
          }
        }
      }
    }
  }
}