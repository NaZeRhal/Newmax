package com.maxrzhe.newmax.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxrzhe.newmax.R
import com.maxrzhe.newmax.theme.NewmaxTheme
import com.maxrzhe.newmax.theme.klasik

@Composable
fun WelcomeScreen() {

  var sizeImage by remember { mutableStateOf(IntSize.Zero) }

  val backgroundGradient = Brush.verticalGradient(
    colors = listOf(Color.White.copy(alpha = 0.8f), Color.Transparent),
    startY = sizeImage.height.toFloat() * 0.1f,
    endY = sizeImage.height.toFloat() * 0.4f
  )
  Box(
    Modifier
      .fillMaxSize()
      .onGloballyPositioned {
        sizeImage = it.size
      },
    contentAlignment = Alignment.TopCenter
  ) {
    Image(
      painter = painterResource(id = R.drawable.splash_screen),
      contentDescription = "splash screen",
      contentScale = ContentScale.Crop
    )
    Box(
      modifier = Modifier
        .matchParentSize()
        .background(backgroundGradient)
    ) {
      Text(
        modifier = Modifier.padding(horizontal = 75.dp, vertical = 96.dp),
        text = "WELCOME TO MONUMENTAL HABITS",
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.displaySmall.copy(
          fontSize = 40.sp,
          lineHeight = 40.sp,
          fontFamily = klasik
        ),
        textAlign = TextAlign.Center
      )
    }
  }
}

@Preview(device = "spec:width=720px,height=1560px,dpi=269")
@Composable
fun SplashScreenPreview() {
  NewmaxTheme {
    WelcomeScreen()
  }

}