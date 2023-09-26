package com.maxrzhe.newmax.screens.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import androidx.compose.ui.unit.dp
import com.maxrzhe.newmax.screens.topics.Categories
import org.koin.androidx.compose.koinViewModel

@Composable
fun CategoriesScreenHoist(viewModel: CategoriesViewModel = koinViewModel()) {
  val state by viewModel.state.collectAsState()

  CategoriesScreen(
    state = state,
    onSelectionChanged = viewModel::onItemSelected
  )
}

@Composable
fun CategoriesScreen(
  state: CategoriesState = CategoriesState(),
  onSelectionChanged: (Int) -> Unit = {}
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row(modifier = Modifier.fillMaxWidth()) {
      Text(
        text = "Thousands of articles in each category",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal),
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
        CategoryItem(
          modifier = Modifier
            .clip(MaterialTheme.shapes.large)
            .clickable { onSelectionChanged(index) },
          data = item,
          isSelected = state.selectedItems.contains(index),
        )
      }
    }
  }
}

@Composable
fun CategoryItem(
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