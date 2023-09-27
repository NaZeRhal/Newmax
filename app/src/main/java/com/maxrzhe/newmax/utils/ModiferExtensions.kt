package com.maxrzhe.newmax.utils

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalDensity

@OptIn(ExperimentalMaterial3Api::class)
fun Modifier.applyTopBarHeight(): Modifier = composed {
  val density = LocalDensity.current
  val topBarHeight = TopAppBarDefaults.windowInsets.asPaddingValues(density).calculateTopPadding()
  val statusBarHeight = WindowInsets.statusBars.asPaddingValues(density).calculateTopPadding()
  this
    .padding(top = topBarHeight + statusBarHeight)

}