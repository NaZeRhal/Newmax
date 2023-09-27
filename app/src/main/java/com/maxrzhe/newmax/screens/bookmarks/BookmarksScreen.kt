package com.maxrzhe.newmax.screens.bookmarks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.maxrzhe.newmax.navigation.Destination
import com.maxrzhe.newmax.navigation.LocalNavController
import com.maxrzhe.newmax.screens.news.ArticleCard
import com.maxrzhe.newmax.utils.applyTopBarHeight
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookmarksScreenHoist(viewModel: BookmarksViewModel = koinViewModel()) {
  val state by viewModel.state.collectAsState()
  val navController = LocalNavController.current

  BookmarksScreen(
    state = state,
    onArticleClick = {
      navController.navigate(Destination.Details(articleId = it))
    }
  )
}

@Composable
fun BookmarksScreen(
  state: BookmarksState = BookmarksState(),
  onArticleClick: (Int) -> Unit = {}
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = 20.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row(modifier = Modifier.fillMaxWidth()) {
      Text(
        text = "Saved articles to the library",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal),
        textAlign = TextAlign.Start
      )
    }
    Spacer(modifier = Modifier.height(32.dp))
    LazyColumn(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      items(state.savedNews) { item ->
        ArticleCard(
          article = item,
          onArticleClick = onArticleClick
        )
      }
      items(state.savedNews) { item ->
        ArticleCard(
          article = item,
          onArticleClick = onArticleClick
        )
      }
    }
  }
}