package com.maxrzhe.newmax.screens.news

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxrzhe.newmax.navigation.Destination
import com.maxrzhe.newmax.navigation.LocalNavController
import com.maxrzhe.newmax.screens.topics.Categories
import com.maxrzhe.newmax.theme.NewmaxTheme
import com.maxrzhe.newmax.theme.onImageBackground
import com.maxrzhe.newmax.theme.onImageText
import org.koin.androidx.compose.koinViewModel


@Composable
fun NewsScreenHoist(viewModel: NewsScreenViewModel = koinViewModel()) {
  val state by viewModel.state.collectAsState()

  val navController = LocalNavController.current

  NewsScreen(
    state = state,
    onSearch = { viewModel.onEvent(NewsScreenEvent.OnSearch(it)) },
    onQueryChange = { viewModel.onEvent(NewsScreenEvent.OnQueryChanged(it)) },
    onActiveChange = { viewModel.onEvent(NewsScreenEvent.OnSearchBarActiveChanged(it)) },
    onChipSelected = { viewModel.onEvent(NewsScreenEvent.OnChipSelected(it)) },
    onMarkArticle = { viewModel.onEvent(NewsScreenEvent.OnMarkArticle(it)) },
    onArticleClick = {
      navController.navigate(Destination.Details(articleId = it))
    }
  )
}

@Composable
fun NewsScreen(
  state: NewsScreenState = NewsScreenState(),
  onQueryChange: (String) -> Unit = {},
  onSearch: (String) -> Unit = {},
  onActiveChange: (Boolean) -> Unit = {},
  onChipSelected: (Int) -> Unit = {},
  onMarkArticle: (Int) -> Unit = {},
  onArticleClick: (Int) -> Unit = {}
) {

  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
    ) {
      Text(
        text = "Discover things of this world",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal),
        textAlign = TextAlign.Start
      )
    }
    Spacer(modifier = Modifier.height(8.dp))
    AppSearchBar(
      query = state.query,
      isActive = state.isSearchBarActive,
      searchHistory = state.searchHistory,
      onQueryChange = onQueryChange,
      onSearch = onSearch,
      onActiveChange = onActiveChange
    )
    TopicChips(
      selectedIndex = state.selectedChipIndex,
      onChipSelected = onChipSelected
    )
    HighlightedNews(
      news = state.news,
      onMarkArticle = onMarkArticle,
      onArticleClick = onArticleClick
    )
    Spacer(modifier = Modifier.height(16.dp))
    RecommendedNews(
      recommendation = state.news,
      onArticleClick = onArticleClick
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSearchBar(
  query: String = "",
  isActive: Boolean = false,
  searchHistory: List<String> = emptyList(),
  onQueryChange: (String) -> Unit = {},
  onSearch: (String) -> Unit = {},
  onActiveChange: (Boolean) -> Unit = {}
) {
  val horizontalPadding by
  animateDpAsState(
    targetValue = if (isActive) 0.dp else 20.dp,
    animationSpec = tween(durationMillis = 500),
    label = "search bar padding animation"
  )

  SearchBar(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = horizontalPadding),
    query = query,
    onQueryChange = onQueryChange,
    onSearch = {
      if (it.isNotEmpty()) {
        onSearch(it)
      }
    },
    active = isActive,
    onActiveChange = onActiveChange,
    windowInsets = WindowInsets.systemBars.only(WindowInsetsSides.Bottom),
    leadingIcon = {
      IconButton(onClick = { onActiveChange(!isActive) }) {
        Icon(
          imageVector = Icons.Outlined.Search,
          contentDescription = "search bar icon",
        )
      }
    },
    trailingIcon = {
      if (isActive) {
        IconButton(onClick = {
          if (query.isEmpty()) {
            onActiveChange(false)
          } else {
            onQueryChange("")
          }
        }) {
          Icon(
            imageVector = Icons.Outlined.Close,
            contentDescription = "close icon",
          )
        }
      }
    },
    placeholder = { Text(text = "Search...") },
  ) {
    LazyColumn(
      modifier = Modifier.padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      items(searchHistory) { item ->
        Row(modifier = Modifier.fillMaxWidth()) {
          Icon(
            modifier = Modifier.padding(end = 14.dp),
            imageVector = Icons.Default.History,
            contentDescription = "history icon"
          )
          Text(text = item)
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicChips(
  selectedIndex: Int = 0,
  onChipSelected: (Int) -> Unit = {}
) {
  LazyRow(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 16.dp),
    horizontalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    itemsIndexed(Categories.values()) { index, item ->
      FilterChip(
        selected = selectedIndex == index,
        shape = MaterialTheme.shapes.extraLarge,
        onClick = { onChipSelected(index) },
        label = { Text(text = item.title) })
    }
  }
}

@Composable
fun HighlightedNews(
  news: List<Article> = emptyList(),
  onMarkArticle: (Int) -> Unit = {},
  onArticleClick: (Int) -> Unit = {}
) {
  val textGradient = Brush.verticalGradient(
    colors = listOf(Color.Transparent, onImageBackground.copy(alpha = 0.8f)),
  )

  LazyRow(
    modifier = Modifier
      .fillMaxWidth()
      .height(256.dp)
      .padding(horizontal = 20.dp),
    horizontalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    items(news) { item ->
      Box(
        modifier = Modifier
          .size(256.dp)
          .background(
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(32.dp)
          )
          .clickable {
            onArticleClick(item.id)
          },
      ) {
        Image(
          modifier = Modifier.clip(RoundedCornerShape(32.dp)),
          painter = painterResource(id = item.image),
          contentDescription = null,
          contentScale = ContentScale.Fit
        )
        Column(
          modifier = Modifier.fillMaxSize(),
          verticalArrangement = Arrangement.SpaceBetween
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(24.dp),
            horizontalArrangement = Arrangement.End
          ) {
            Icon(
              modifier = Modifier
                .size(24.dp)
                .clickable { onMarkArticle(item.id) },
              imageVector = if (item.marked) Icons.Outlined.Bookmark else Icons.Outlined.BookmarkBorder,
              contentDescription = "mark icon",
              tint = onImageText
            )
          }
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .background(
                brush = textGradient,
                shape = RoundedCornerShape(32.dp)
              )
              .padding(24.dp),
            horizontalAlignment = Alignment.Start
          ) {
            Text(
              text = item.topic.uppercase(),
              style = MaterialTheme.typography.bodySmall,
              color = onImageText
            )
            Text(
              text = item.title,
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
              ),
              color = onImageText
            )
          }
        }
      }
    }
  }
}

@Composable
fun RecommendedNews(
  recommendation: List<Article> = emptyList(),
  onArticleClick: (Int) -> Unit = {}
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 20.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      text = "Recommended for you",
      style = MaterialTheme.typography.titleLarge.copy(
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
      )
    )
    Text(
      text = "See All",
      style = MaterialTheme.typography.titleSmall,
      color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
    )
  }
  LazyColumn(
    modifier = Modifier
      .fillMaxWidth()
      .padding(start = 20.dp, end = 20.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(recommendation) { item ->
      ArticleCard(
        article = item,
        onArticleClick = onArticleClick
      )
    }
  }
}

@Composable
fun ArticleCard(
  article: Article,
  onArticleClick: (Int) -> Unit
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(96.dp)
      .clickable { onArticleClick(article.id) },
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Image(
      modifier = Modifier.size(96.dp),
      painter = painterResource(id = article.image),
      contentDescription = "article image",
      contentScale = ContentScale.Fit
    )
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 4.dp),
      horizontalAlignment = Alignment.Start
    ) {
      Text(
        text = article.topic,
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal)
      )
      Text(
        text = article.title,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
      )
    }
  }
}


@Preview(showSystemUi = true, showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun NewsScreenPreview() {
  NewmaxTheme {
    NewsScreen()
  }
}