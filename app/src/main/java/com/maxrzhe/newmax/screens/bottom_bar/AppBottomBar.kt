package com.maxrzhe.newmax.screens.bottom_bar

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ViewComfyAlt
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ViewComfyAlt
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.maxrzhe.newmax.R
import com.maxrzhe.newmax.navigation.LocalNavController
import com.maxrzhe.newmax.navigation.Screen

@Composable
fun AppBottomBarHoist() {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomBar() {

  val navController = LocalNavController.current
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination

  NavigationBar {
    bottomNavigationItems.forEach { item ->
      val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
      NavigationBarItem(
        selected = selected,
        onClick = {
          navController.navigate(item.route) {
            popUpTo(navController.graph.findStartDestination().id) {
              saveState = true
            }
            launchSingleTop = true
            restoreState = true
          }
        },
        label = { Text(text = stringResource(id = item.titleResId)) },
        icon = {
          BadgedBox(badge = {
            if (item.hasNews) {
              Badge()
            }
          }) {
            Icon(
              modifier = Modifier.size(28.dp),
              imageVector = if (selected) {
                item.selectedIcon
              } else {
                item.unselectedIcon
              },
              contentDescription = stringResource(id = item.titleResId)
            )
          }
        }
      )
    }
  }
}

data class BottomNavigationItem(
  @StringRes val titleResId: Int,
  val selectedIcon: ImageVector,
  val unselectedIcon: ImageVector,
  val hasNews: Boolean,
  val route: String
)

val bottomNavigationItems = listOf(
  BottomNavigationItem(
    titleResId = R.string.home,
    selectedIcon = Icons.Filled.Home,
    unselectedIcon = Icons.Outlined.Home,
    hasNews = false,
    route = Screen.NewsList.route
  ),
  BottomNavigationItem(
    titleResId = R.string.categories,
    selectedIcon = Icons.Filled.ViewComfyAlt,
    unselectedIcon = Icons.Outlined.ViewComfyAlt,
    hasNews = false,
    route = Screen.Categories.route
  ),
  BottomNavigationItem(
    titleResId = R.string.bookmarks,
    selectedIcon = Icons.Filled.Bookmark,
    unselectedIcon = Icons.Outlined.BookmarkBorder,
    hasNews = false,
    route = Screen.Bookmarks.route
  ),
  BottomNavigationItem(
    titleResId = R.string.profile,
    selectedIcon = Icons.Filled.Person,
    unselectedIcon = Icons.Outlined.Person,
    hasNews = true,
    route = Screen.Profile.route
  ),
)