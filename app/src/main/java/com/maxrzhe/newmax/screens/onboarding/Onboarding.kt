package com.maxrzhe.newmax.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxrzhe.newmax.theme.NewmaxTheme
import com.maxrzhe.newmax.theme.klasik
import com.maxrzhe.newmax.theme.lightYellow


@Composable
fun OnboardingHoist() {


}

@Composable
fun Onboarding() {
  var pageIndex by remember { mutableStateOf(0) }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .padding(top = 70.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .height(580.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        modifier = Modifier.padding(horizontal = 32.dp),
        text = OnboardingData.values()[pageIndex].title,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.headlineLarge.copy(
          fontFamily = klasik,
          lineHeight = 32.sp
        ),
        textAlign = TextAlign.Center
      )
      Image(
        modifier = Modifier
          .fillMaxHeight()
          .fillMaxWidth(),
        painter = painterResource(id = OnboardingData.values()[pageIndex].resource),
        contentDescription = "splash screen",
//        contentScale = ContentScale.FillWidth
      )
    }
    Text(
      modifier = Modifier.padding(start = 32.dp, end = 32.dp),
      text = buildAnnotatedString {
        append("WE CAN ")
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
          append("HELP YOU ")
        }
        append("TO BE BETTER VERSION OF ")

        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary))
        {
          append("YOURSELF.")
        }
      },
      color = MaterialTheme.colorScheme.onBackground,
      style = MaterialTheme.typography.bodyLarge.copy(
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold
      ),
      textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.weight(1f))
    if (pageIndex == OnboardingData.values().lastIndex) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp, vertical = 36.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Button(
          modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
              color = MaterialTheme.colorScheme.primary,
              shape = RoundedCornerShape(10.dp)
            ),
          onClick = {

          }) {
          Text(
            text = "Get Started",
            style = MaterialTheme.typography.bodyLarge.copy(
              fontWeight = FontWeight.Bold,
              lineHeight = 16.sp
            ),
            color = MaterialTheme.colorScheme.onBackground
          )
        }
      }
    } else {
      OnboardingPath(
        index = pageIndex,
        onIndexChanged = { pageIndex = it }
      )
    }
  }
}

@Composable
fun YellowCircle() {
  Box(
    modifier = Modifier
      .size(11.dp)
      .background(
        color = lightYellow,
        shape = RoundedCornerShape(50.dp)
      )
  )
}

@Composable
fun ChosenCircle() {
  Box(contentAlignment = Alignment.Center) {
    Box(
      modifier = Modifier
        .size(17.dp)
        .background(
          color = Color.LightGray,
          shape = RoundedCornerShape(50.dp)
        )
    )
    Box(
      modifier = Modifier
        .size(11.dp)
        .background(
          color = MaterialTheme.colorScheme.onBackground,
          shape = RoundedCornerShape(50.dp)
        )
    )
  }
}

@Composable
fun OnboardingPath(index: Int = 0, onIndexChanged: (Int) -> Unit = {}) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 24.dp, vertical = 40.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    if (index == OnboardingData.values().lastIndex) {
      Button(onClick = { }) {

      }
    } else {
      TextButton(onClick = {
        if (index > 0) {
          onIndexChanged(index - 1)
        }
      }) {
        Text(
          text = "Skip",
          color = MaterialTheme.colorScheme.onBackground,
          style = MaterialTheme.typography.bodyLarge.copy(
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
          ),
        )
      }
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        repeat(4) {
          if (it == index) {
            ChosenCircle()
          } else {
            YellowCircle()
          }
        }
      }
      TextButton(onClick = {
        if (index < OnboardingData.values().size - 1) {
          onIndexChanged(index + 1)
        }
      }) {
        Text(
          text = "Next",
          color = MaterialTheme.colorScheme.onBackground,
          style = MaterialTheme.typography.bodyLarge.copy(
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
          ),
        )
      }
    }
  }
}

@Preview(device = "spec:width=720px,height=1560px,dpi=269", showSystemUi = true)
@Preview(device = "spec:width=411dp,height=891dp", showSystemUi = true)
@Preview(
  device = "spec:width=1280dp,height=800dp,dpi=240,orientation=portrait",
  showSystemUi = true
)
@Composable
fun OnboardingPreview() {
  NewmaxTheme {
    Onboarding()
  }

}