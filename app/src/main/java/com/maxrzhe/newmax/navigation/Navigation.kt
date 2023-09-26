package com.maxrzhe.newmax.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.maxrzhe.newmax.R
import com.maxrzhe.newmax.navigation.Screen.Bookmarks
import com.maxrzhe.newmax.navigation.Screen.Categories
import com.maxrzhe.newmax.navigation.Screen.NewsList
import com.maxrzhe.newmax.navigation.Screen.Profile
import com.maxrzhe.newmax.navigation.Screen.Topics
import com.maxrzhe.newmax.screens.bookmarks.BookmarksScreenHoist
import com.maxrzhe.newmax.screens.categories.CategoriesScreenHoist
import com.maxrzhe.newmax.screens.news.NewsScreenHoist
import com.maxrzhe.newmax.screens.profile.ProfileScreenHoist
import com.maxrzhe.newmax.screens.topics.TopicsSelectionScreenHoist
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

const val ROOT_GRAPH = "root_graph"
const val TOPICS_SELECTION_GRAPH = "topics_selection_graph"
const val NEWS_GRAPH = "news_graph"

val LocalNavController = compositionLocalOf<NavController> { error("No NavController found!") }

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
  data object Topics : Screen("topics", R.string.topics)
  data object NewsList : Screen("news_list", R.string.news_list)
  data object Categories : Screen("categories", R.string.categories)
  data object Bookmarks : Screen("bookmarks", R.string.bookmarks)
  data object Profile : Screen("profile", R.string.profile)

  companion object {
    fun fromRoute(route: String?): Screen? =
      when (route) {
        Topics.route -> Topics
        NewsList.route -> NewsList
        Categories.route -> Categories
        Bookmarks.route -> Bookmarks
        Profile.route -> Profile
        else -> null
      }
  }
}

fun NavGraphBuilder.topicsSelectionNavGraph() {
  navigation(
    startDestination = Topics.route,
    route = TOPICS_SELECTION_GRAPH
  ) {
    composable(route = Topics.route) {
      TopicsSelectionScreenHoist()
    }
  }
}

fun NavGraphBuilder.newsNavGraph() {
  navigation(
    startDestination = NewsList.route,
    route = NEWS_GRAPH
  ) {
    composable(route = NewsList.route) {
//      val viewModel = it.sharedViewModel<NewsScreenViewModel>(navController = navController)
      NewsScreenHoist()
    }
    composable(route = Categories.route) {
      CategoriesScreenHoist()
    }
    composable(route = Bookmarks.route) {
      BookmarksScreenHoist()
    }
    composable(route = Profile.route) {
      ProfileScreenHoist()
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