package com.maxrzhe.newmax.screens.topics

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxrzhe.newmax.navigation.LocalNavController
import com.maxrzhe.newmax.navigation.NEWS_GRAPH
import com.maxrzhe.newmax.navigation.TOPICS_SELECTION_GRAPH
import com.maxrzhe.newmax.theme.NewmaxTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun TopicsSelectionScreenHoist(viewModel: TopicsSelectionViewModel = koinViewModel()) {
  val state by viewModel.state.collectAsState()

  TopicsSelection(
    state = state,
    onSelectionChanged = {
      viewModel.onEvent(TopicsSelectionEvent.OnSelectionChanged(it))
    }
  )
}

@Composable
fun TopicsSelection(
  state: TopicsSelectionState = TopicsSelectionState(),
  onSelectionChanged: (Int) -> Unit = {},
) {

  val navController = LocalNavController.current

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = 20.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Row(
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(
        text = "Select some of your favorite topics to let us suggest better news for you",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal),
//        color = MaterialTheme.colorScheme.onSurfaceVariant,
        textAlign = TextAlign.Start
      )
    }
    Spacer(modifier = Modifier.height(32.dp))
    LazyVerticalGrid(
      columns = GridCells.Fixed(2),
      userScrollEnabled = false,
      horizontalArrangement = Arrangement.spacedBy(20.dp),
      verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
      itemsIndexed(Categories.values()) { index, item ->
        TopicSelectionItem(
          modifier = Modifier
            .clip(MaterialTheme.shapes.large)
            .clickable { onSelectionChanged(index) },
          data = item,
          isSelected = state.selectedItems.contains(index),
        )
      }
    }
    Spacer(modifier = Modifier.height(64.dp))
    Button(
      modifier = Modifier
        .fillMaxWidth()
        .height(56.dp),
      shape = MaterialTheme.shapes.large,
//      colors = ButtonDefaults.buttonColors(
//        containerColor = MaterialTheme.colorScheme.primary,
//        contentColor = MaterialTheme.colorScheme.onPrimary
//      ),
      onClick = {
        navController.navigate(NEWS_GRAPH) {
          popUpTo(TOPICS_SELECTION_GRAPH) {
            inclusive = true
          }
        }
      }
    ) {
      Text(
        text = "Next", style = MaterialTheme.typography.titleMedium.copy(
          fontWeight = FontWeight.SemiBold
        )
      )
    }
  }
}

@Composable
fun TopicSelectionItem(
  modifier: Modifier = Modifier,
  data: Categories,
  isSelected: Boolean
) {
  Box(
    modifier = modifier
      .width(160.dp)
      .height(72.dp)
      .background(
        color = if (isSelected)
          MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.surface,
        shape = MaterialTheme.shapes.large
      )
      .border(
        width = 1.dp,
        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
        shape = MaterialTheme.shapes.large
      ),
    contentAlignment = Alignment.Center
  ) {
    Text(
      text = "${data.icon}      ${data.title}",
      style = MaterialTheme.typography.titleMedium.copy(
        fontWeight = FontWeight.SemiBold
      ),
      color = if (isSelected)
        MaterialTheme.colorScheme.onPrimary
      else MaterialTheme.colorScheme.onSurface
    )
  }
}

@Preview(showSystemUi = true, showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun TopicsSelectionPreview() {
  NewmaxTheme {
    TopicsSelection()
  }
}