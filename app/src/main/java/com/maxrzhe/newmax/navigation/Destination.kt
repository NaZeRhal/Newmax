package com.maxrzhe.newmax.navigation

import androidx.annotation.StringRes
import com.maxrzhe.newmax.R


const val ROOT_GRAPH = "root_graph"
const val TOPICS_SELECTION_GRAPH = "topics_selection_graph"
const val HOME_GRAPH = "home_graph"
const val NEWS_GRAPH = "news_graph"

sealed class Destination(
  protected val route: String,
  @StringRes val titleRes: Int? = null,
  vararg params: String
) {
  val fullRoute: String = if (params.isEmpty()) route else {
    val builder = StringBuilder(route)
    params.forEach { builder.append("/{${it}}") }
    builder.toString()
  }

  sealed class NoArgumentsDestination(route: String, titleRes: Int?) :
    Destination(route = route, titleRes = titleRes) {
    operator fun invoke(): String = route
  }

  data object Topics : NoArgumentsDestination(
    route = "topics",
    titleRes = R.string.topics
  )

  data object NewsList : NoArgumentsDestination(
    route = "news_list",
    titleRes = R.string.news_list
  )

  data object Categories : NoArgumentsDestination(
    route = "categories",
    titleRes = R.string.categories
  )

  data object Bookmarks : NoArgumentsDestination(
    route = "bookmarks",
    titleRes = R.string.bookmarks
  )

  data object Profile : NoArgumentsDestination(
    route = "profile",
    titleRes = R.string.profile
  )

  data object Details : Destination(
    route = "details",
    titleRes = R.string.details,
    params = arrayOf("articleId")
  ) {
    operator fun invoke(articleId: Int): String =
      route.appendParams(ARTICLE_ID to articleId)
  }

  companion object {
    const val ARTICLE_ID = "articleId"

    fun fromRoute(route: String?): Destination? =
      when (route) {
        Topics.fullRoute -> Topics
        NewsList.fullRoute -> NewsList
        Categories.fullRoute -> Categories
        Bookmarks.fullRoute -> Bookmarks
        Profile.fullRoute -> Profile
        Details.fullRoute -> Details
        else -> null
      }
  }
}

internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
  val builder = StringBuilder(this)

  params.forEach {
    it.second?.toString()?.let { arg ->
      builder.append("/$arg")
    }
  }

  return builder.toString()
}

