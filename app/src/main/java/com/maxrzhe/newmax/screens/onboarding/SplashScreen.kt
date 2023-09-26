package com.maxrzhe.newmax.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.maxrzhe.newmax.R

@Composable
fun AppSplashScreen() {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(color = MaterialTheme.colorScheme.primary),
    contentAlignment = Alignment.Center
  ) {
    Image(
      modifier = Modifier.size(64.dp),
      painter = painterResource(id = R.drawable.logo),
      contentDescription = "logo icon"
    )
  }
}