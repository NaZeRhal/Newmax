package com.maxrzhe.newmax.data.mappers

import com.maxrzhe.newmax.core.models.ArticleModel
import com.maxrzhe.newmax.core.models.NewsCategory
import com.maxrzhe.newmax.core.models.SourceModel
import com.maxrzhe.newmax.data.remote.dto.Article
import database.TopHeadlineEntity
import java.util.UUID

fun Article.toEntity(category: String): TopHeadlineEntity = TopHeadlineEntity(
  id = UUID.randomUUID().toString(),
  author = author,
  content = content,
  description = description,
  publishedAt = publishedAt,
  title = title,
  url = url,
  urlToImage = urlToImage,
  sourceId = source?.id,
  sourceName = source?.name,
  category = category,
)

fun TopHeadlineEntity.toModel(): ArticleModel = ArticleModel(
  id = id,
  author = author,
  content = content,
  description = description,
  publishedAt = publishedAt,
  title = title,
  url = url,
  urlToImage = urlToImage,
  category = NewsCategory.valueOf(category.uppercase()),
  source = SourceModel(
    id = sourceId,
    name = sourceName
  )
)