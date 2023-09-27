package com.maxrzhe.newmax.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreenHoist(viewModel: DetailsScreenViewModel = koinViewModel()) {
  val state by viewModel.state.collectAsState()

  DetailsScreen(state = state)
}

@Composable
fun DetailsScreen(
  state: DetailsScreenState = DetailsScreenState()
) {
  Column(
    modifier = Modifier.fillMaxSize()
  ) {
    println(state.article)
    state.article?.run {
      Image(
        modifier = Modifier.size(250.dp),
        painter = painterResource(id = image),
        contentDescription = title
      )
      Text(text = topic)
      Text(text = title)
    }
  }
}