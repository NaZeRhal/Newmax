package com.maxrzhe.newmax.data_api.repository

import com.maxrzhe.newmax.common.Resource
import com.maxrzhe.newmax.core.models.ArticleModel
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

  fun getTopHeadlines(
    shouldFetchFromRemote: Boolean,
    query: String,
    category: String
  ): Flow<Resource<List<ArticleModel>>>
}