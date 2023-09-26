package com.maxrzhe.newmax.screens.header

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.maxrzhe.newmax.navigation.LocalNavController
import com.maxrzhe.newmax.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {

  val navController = LocalNavController.current
  val currentBackStackEntry by navController.currentBackStackEntryAsState()
  var titleResId by remember { mutableStateOf<Int?>(null) }

  LaunchedEffect(currentBackStackEntry) {
    val route = currentBackStackEntry?.destination?.route
    val screen = Screen.fromRoute(route)
    titleResId = screen?.resourceId
  }

  TopAppBar(
    title = {
      titleResId?.let {
        Text(
          modifier = Modifier.padding(horizontal = 4.dp),
          text = stringResource(id = it),
          style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.SemiBold),
          textAlign = TextAlign.Start
        )
      }
    },
  )
}