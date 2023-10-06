package com.maxrzhe.newmax.data.local

import database.TopHeadlineEntity

interface NewsDataSource {

  suspend fun insertTopHeadlines(topHeadlines: List<TopHeadlineEntity>)

  suspend fun clearTopHeadlines()

  suspend fun clearTopHeadlinesByCategory(category: String)

  suspend fun searchTopHeadlines(category: String, query: String): List<TopHeadlineEntity>

  suspend fun getTopHeadlineById(articleId: String): TopHeadlineEntity?
}