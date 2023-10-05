package com.maxrzhe.newmax.core.models

import kotlinx.serialization.Serializable


@Serializable
data class ArticleModel(
    val id: Long,
    val author: String?,
    val publishedAt: String?,
    val source: SourceModel?,
    val title: String?,
    val description: String?,
    val content: String?,
    val url: String?,
    val urlToImage: String?,
    val category: NewsCategory
)