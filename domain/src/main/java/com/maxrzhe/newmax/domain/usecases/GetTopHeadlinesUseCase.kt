package com.maxrzhe.newmax.domain.usecases

import com.maxrzhe.newmax.common.Resource
import com.maxrzhe.newmax.core.models.ArticleModel
import com.maxrzhe.newmax.data_api.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetTopHeadlinesUseCase(private val repository: NewsRepository) {

  fun execute(
    shouldFetchFromRemote: Boolean,
    query: String,
    category: String
  ): Flow<Resource<List<ArticleModel>>> = repository.getTopHeadlines(
    shouldFetchFromRemote = shouldFetchFromRemote,
    query = query,
    category = category
  )
}