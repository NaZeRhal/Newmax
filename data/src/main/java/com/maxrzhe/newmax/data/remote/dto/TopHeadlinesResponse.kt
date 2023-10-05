package com.maxrzhe.newmax.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TopHeadlinesResponse(
  val articles: List<Article?>?,
  val status: String?,
  val totalResults: Int?
)