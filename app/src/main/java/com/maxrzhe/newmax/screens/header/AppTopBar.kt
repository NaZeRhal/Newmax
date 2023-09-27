package com.maxrzhe.newmax.screens.header

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.maxrzhe.newmax.navigation.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(destination: Destination?) {

  TopAppBar(
    title = {
      destination?.titleRes?.let {
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