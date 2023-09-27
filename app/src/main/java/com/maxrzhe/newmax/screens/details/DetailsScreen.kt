package com.maxrzhe.newmax.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxrzhe.newmax.R
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
  LazyColumn(
    modifier = Modifier
      .fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    item {
      state.article?.run {
        Column(modifier = Modifier.fillMaxSize()) {
          Image(
            modifier = Modifier
              .fillMaxWidth()
              .height(300.dp),
            painter = painterResource(id = image),
            contentDescription = title,
            contentScale = ContentScale.Crop
          )
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(20.dp)
          ) {
            Row(
              modifier = Modifier
                .fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Row(
                modifier = Modifier
                  .defaultMinSize(minWidth = 100.dp)
                  .height(50.dp)
                  .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(50.dp)
                  )
                  .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(
                  text = state.article.topic,
                  style = MaterialTheme.typography.titleMedium,
                  color = MaterialTheme.colorScheme.onPrimary
                )
              }
              Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
              ) {
                Image(
                  painter = painterResource(id = R.drawable.user_one),
                  contentDescription = "user",
                  modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                  Text(
                    text = "Vasiliy Lipinski",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                  )
                  Text(
                    text = "WallStreet Journal",
                    style = MaterialTheme.typography.titleSmall
                  )
                }
              }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
              text = title,
              style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
              ),
              textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
              text = state.article.text,
              style = MaterialTheme.typography.bodyLarge,
              textAlign = TextAlign.Justify
            )
          }

        }
      }
    }
  }
}