package com.maxrzhe.newmax.data.local

import com.maxrzhe.newmax.data.database.NewsDatabase
import database.TopHeadlineEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsDataSourceImpl(
  db: NewsDatabase
) : NewsDataSource {

  private val queries = db.newsQueries

  override suspend fun insertTopHeadlines(topHeadlines: List<TopHeadlineEntity>) {
    return withContext(Dispatchers.IO) {
      queries.transaction {
        topHeadlines.forEach { topHeadline ->
          queries.insertTopHeadline(topHeadline)
        }
      }
    }
  }

  override suspend fun clearTopHeadlines() {
    withContext(Dispatchers.IO) {
      queries.deleteAll()
    }
  }

  override suspend fun clearTopHeadlinesByCategory(category: String) {
    withContext(Dispatchers.IO) {
      queries.deleteByCategory(category = category)
    }
  }

  override suspend fun searchTopHeadlines(
    category: String,
    query: String
  ): List<TopHeadlineEntity> {

    return withContext(Dispatchers.IO) {
      queries.searchTopHeadlines(
        category = category,
        query = query
      ).executeAsList()
    }
  }

  override suspend fun getTopHeadlineById(articleId: String): TopHeadlineEntity? {
    return withContext(Dispatchers.IO) {
      queries.getById(articleId).executeAsOneOrNull()
    }
  }
}