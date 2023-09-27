package com.maxrzhe.newmax.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.maxrzhe.newmax.theme.utils.fadeInTween500
import com.maxrzhe.newmax.theme.utils.fadeOutTween500

fun NavGraphBuilder.composable(
  destination: Destination,
  arguments: List<NamedNavArgument> = emptyList(),
  deepLinks: List<NavDeepLink> = emptyList(),
  enterTransition: (@JvmSuppressWildcards
  AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?) = { fadeInTween500 },
  exitTransition: (@JvmSuppressWildcards
  AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = { fadeOutTween500 },
  popEnterTransition: (@JvmSuppressWildcards
  AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
  popExitTransition: (@JvmSuppressWildcards
  AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
  content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
  composable(
    route = destination.fullRoute,
    arguments = arguments,
    deepLinks = deepLinks,
    enterTransition = enterTransition,
    exitTransition = exitTransition,
    popEnterTransition = popEnterTransition,
    popExitTransition = popExitTransition,
    content = content
  )
}

fun NavGraphBuilder.navigation(
  destination: Destination,
  route: String,
  enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?) = { fadeInTween500 },
  exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?) = { fadeOutTween500 },
  popEnterTransition: (
  AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?
  )? = enterTransition,
  popExitTransition: (
  AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?
  )? = exitTransition,
  builder: NavGraphBuilder.() -> Unit
) {
  navigation(
    startDestination = destination.fullRoute,
    route = route,
    enterTransition = enterTransition,
    exitTransition = exitTransition,
    popEnterTransition = popEnterTransition,
    popExitTransition = popExitTransition,
    builder = builder
  )
}
