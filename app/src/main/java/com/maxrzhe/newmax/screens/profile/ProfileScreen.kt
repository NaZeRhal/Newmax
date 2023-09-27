package com.maxrzhe.newmax.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.maxrzhe.newmax.R

@Composable
fun ProfileScreenHoist() {
  ProfileScreen()
}

@Composable
fun ProfileScreen() {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(20.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Image(
        painter = painterResource(id = R.drawable.user_one),
        contentDescription = "user",
        modifier = Modifier
          .size(72.dp)
          .clip(CircleShape)
      )
      Spacer(modifier = Modifier.width(16.dp))
      Column {
        Text(
          text = "Maksim Rzhevutski",
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Text(
          text = "Mobile Developer",
          style = MaterialTheme.typography.titleSmall
        )
      }
    }
    Spacer(modifier = Modifier.height(24.dp))
    LazyColumn(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
      items(buttonNames) {
        ProfileButton(name = it)
      }
    }
  }
}

@Composable
fun ProfileButton(
  name: String,
  onClick: () -> Unit = {}
) {
  Button(
    colors = ButtonDefaults.buttonColors(
      containerColor = MaterialTheme.colorScheme.surfaceVariant,
      contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    ),
    shape = MaterialTheme.shapes.medium,
    onClick = onClick
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .height(56.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = name,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
      )
      Icon(
        imageVector = Icons.Outlined.KeyboardArrowRight,
        contentDescription = "arrow next",
        modifier = Modifier.size(24.dp)
      )
    }
  }
}

val buttonNames = listOf(
  "Language",
  "Change Password",
  "Privacy",
  "Terms & Conditions",
  "Sign Out"
)