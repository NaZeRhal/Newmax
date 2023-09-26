package com.maxrzhe.newmax.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

val klasik = FontFamily(
  Font(R.font.klasik_regular, FontWeight.Normal)
)

val manrope = FontFamily(
  Font(R.font.manrope_extra_light, FontWeight.ExtraLight),
  Font(R.font.manrope_light, FontWeight.Light),
  Font(R.font.manrope_regular, FontWeight.Normal),
  Font(R.font.manrope_medium, FontWeight.Medium),
  Font(R.font.manrope_semibold, FontWeight.SemiBold),
  Font(R.font.manrope_bold, FontWeight.Bold),
  Font(R.font.manrope_extra_bold, FontWeight.ExtraBold),

  )

val Typography = Typography(
  displayLarge = TextStyle(
    fontFamily = manrope,
    fontSize = 57.sp,
    lineHeight = 64.sp,
    fontWeight = FontWeight.Normal,
    letterSpacing = (-0.25).sp
  ),
  displayMedium = TextStyle(
    fontFamily = manrope,
    fontSize = 45.sp,
    lineHeight = 52.sp,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.sp
  ),
  displaySmall = TextStyle(
    fontFamily = manrope,
    fontSize = 36.sp,
    lineHeight = 44.sp,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.sp
  ),
  headlineLarge = TextStyle(
    fontFamily = manrope,
    fontSize = 32.sp,
    lineHeight = 40.sp,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.sp
  ),
  headlineMedium = TextStyle(
    fontFamily = manrope,
    fontSize = 28.sp,
    lineHeight = 36.sp,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.sp
  ),
  headlineSmall = TextStyle(
    fontFamily = manrope,
    fontSize = 24.sp,
    lineHeight = 32.sp,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.sp,
  ),
  titleLarge = TextStyle(
    fontFamily = manrope,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.sp,
  ),
  titleMedium = TextStyle(
    fontFamily = manrope,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    fontWeight = FontWeight.Medium,
    letterSpacing = 0.15.sp,
  ),
  titleSmall = TextStyle(
    fontFamily = manrope,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    fontWeight = FontWeight.Medium,
    letterSpacing = 0.1.sp,
  ),
  labelLarge = TextStyle(
    fontFamily = manrope,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    fontWeight = FontWeight.Medium,
    letterSpacing = 0.1.sp
  ),
  labelMedium = TextStyle(
    fontFamily = manrope,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    fontWeight = FontWeight.Medium,
    letterSpacing = 0.5.sp
  ),
  labelSmall = TextStyle(
    fontFamily = manrope,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    fontWeight = FontWeight.Medium,
    letterSpacing = 0.5.sp
  ),
  bodyLarge = TextStyle(
    fontFamily = manrope,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.5.sp,
  ),
  bodyMedium = TextStyle(
    fontFamily = manrope,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.25.sp,
  ),
  bodySmall = TextStyle(
    fontFamily = manrope,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.4.sp,
  ),
)