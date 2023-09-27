package com.maxrzhe.newmax.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.maxrzhe.newmax.screens.bookmarks.BookmarksScreenHoist
import com.maxrzhe.newmax.screens.categories.CategoriesScreenHoist
import com.maxrzhe.newmax.screens.details.DetailsScreenHoist
import com.maxrzhe.newmax.screens.news.NewsScreenHoist
import com.maxrzhe.newmax.screens.profile.ProfileScreenHoist
import com.maxrzhe.newmax.screens.topics.TopicsSelectionScreenHoist
import com.maxrzhe.newmax.theme.utils.fadeInTween500
import com.maxrzhe.newmax.theme.utils.fadeOutTween500
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


val LocalNavController = compositionLocalOf<NavController> { error("No NavController found!") }

fun NavGraphBuilder.topicsSelectionNavGraph() {
  navigation(
    destination = Destination.Topics,
    route = TOPICS_SELECTION_GRAPH
  ) {
    composable(destination = Destination.Topics) {
      TopicsSelectionScreenHoist()
    }
  }
}

fun NavGraphBuilder.homeNavGraph() {
  navigation(
    startDestination = NEWS_GRAPH,
    route = HOME_GRAPH,
    enterTransition = { fadeInTween500 },
    exitTransition = { fadeOutTween500 },
  ) {
    newsNavGraph()
    composable(destination = Destination.Categories) {
      CategoriesScreenHoist()
    }
    composable(destination = Destination.Bookmarks) {
      BookmarksScreenHoist()
    }
    composable(destination = Destination.Profile) {
      ProfileScreenHoist()
    }
  }
}

fun NavGraphBuilder.newsNavGraph() {
  navigation(
    destination = Destination.NewsList,
    route = NEWS_GRAPH
  ) {
    composable(destination = Destination.NewsList) {
//      val viewModel = it.sharedViewModel<NewsScreenViewModel>(navController = navController)
      NewsScreenHoist()
    }
    composable(destination = Destination.Details) {
      DetailsScreenHoist()
    }
  }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
  val navGraphRoute = destination.parent?.route ?: return koinViewModel()
  val parentEntry = remember(this) {
    navController.getBackStackEntry(navGraphRoute)
  }
  return koinViewModel(parameters = { parametersOf(parentEntry) })
}