package com.maxrzhe.newmax.data.remote.api

import com.maxrzhe.newmax.data.remote.dto.Article

interface NewsService {
  suspend fun getTopHeadlines(category: String): List<Article?>
}