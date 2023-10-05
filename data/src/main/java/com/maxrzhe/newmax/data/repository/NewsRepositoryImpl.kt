package com.maxrzhe.newmax.data.repository

import android.util.Log
import com.maxrzhe.newmax.common.Constants.TAG
import com.maxrzhe.newmax.common.Resource
import com.maxrzhe.newmax.core.models.ArticleModel
import com.maxrzhe.newmax.data.remote.api.NewsService
import com.maxrzhe.newmax.data_api.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow

class NewsRepositoryImpl(
  val service: NewsService
) : NewsRepository {
  override fun getTopHeadlines(
    shouldFetchFromRemote: Boolean,
    query: String,
    category: String
  ): Flow<Resource<List<ArticleModel>>> = flow {
    Log.w(TAG, "getTopHeadlines: category = $category")
    emit(Resource.Loading(true))

    val topHeadlines = service.getTopHeadlines(category = category).filterNotNull()


  }
}