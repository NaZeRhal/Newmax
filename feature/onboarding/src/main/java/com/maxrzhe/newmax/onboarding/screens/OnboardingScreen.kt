package com.maxrzhe.newmax.onboarding.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OnBoardingScreen() {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) {
    Spacer(modifier = Modifier.height(120.dp))
    LazyRow(modifier = Modifier.height(336.dp)) {

    }
    Spacer(modifier = Modifier.height(32.dp))
    Row(modifier = Modifier.height(8.dp)) {

    }
    Spacer(modifier = Modifier.height(32.dp))
    Text(
      text = "First to know",

    )
  }
}