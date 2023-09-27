package com.maxrzhe.newmax.theme.utils

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

val fadeInTween500 = fadeIn(animationSpec = tween(durationMillis = 400))
val fadeOutTween500 = fadeOut(animationSpec = tween(durationMillis = 400))