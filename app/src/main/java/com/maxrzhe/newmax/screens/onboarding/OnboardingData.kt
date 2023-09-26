package com.maxrzhe.newmax.screens.onboarding

import androidx.annotation.DrawableRes
import com.maxrzhe.newmax.R

enum class OnboardingData(
  val title: String,
  @DrawableRes val resource: Int,
) {
  WELCOME(
    title = "WELCOME TO MONUMENTAL HABITS",
    resource = R.drawable.onboarding_one,
  ),
  NEW_HABIT(
    title = "CREATE NEW HABIT EASILY",
    resource = R.drawable.onboarding_two
  ),
  PROGRESS(
    title = "KEEP YOUR TRACK PROGRESS",
    resource = R.drawable.onboarding_three
  ),
  COMMUNITY(
    title = "JOIN A SUPPORTIVE COMMUNITY",
    resource = R.drawable.onboarding_four
  )
}