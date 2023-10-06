package com.maxrzhe.newmax.core.models

import kotlinx.serialization.Serializable


@Serializable
data class ArticleModel(
  val id: String,
  val author: String?,
  val title: String?,
  val description: String?,
  val content: String?,
  val source: SourceModel?,
  val url: String?,
  val urlToImage: String?,
  val category: NewsCategory,
  val publishedAt: String?
)