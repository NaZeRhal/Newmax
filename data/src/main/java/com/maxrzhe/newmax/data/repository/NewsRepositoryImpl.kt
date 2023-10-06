package com.maxrzhe.newmax.data.repository

import android.util.Log
import com.maxrzhe.newmax.common.Constants.TAG
import com.maxrzhe.newmax.common.Resource
import com.maxrzhe.newmax.core.models.ArticleModel
import com.maxrzhe.newmax.data.local.NewsDataSource
import com.maxrzhe.newmax.data.mappers.toEntity
import com.maxrzhe.newmax.data.mappers.toModel
import com.maxrzhe.newmax.data.remote.api.NewsService
import com.maxrzhe.newmax.data_api.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepositoryImpl(
  private val service: NewsService,
  private val localDataSource: NewsDataSource
) : NewsRepository {
  override fun getTopHeadlines(
    shouldFetchFromRemote: Boolean,
    query: String,
    category: String
  ): Flow<Resource<List<ArticleModel>>> = flow {
    Log.w(TAG, "getTopHeadlines: category = $category")
    emit(Resource.Loading(true))

    val localTopHeadlines = localDataSource.searchTopHeadlines(
      category = category,
      query = query
    )
    Log.w(TAG, "topHeadlineNews: ${localTopHeadlines.map { it.category }}")
    emit(Resource.Success(data = localTopHeadlines.map { it.toModel() }))

    val isDbEmpty = localTopHeadlines.isEmpty() && query.isBlank()

    if (!isDbEmpty && !shouldFetchFromRemote) {
      emit(Resource.Loading(false))
      return@flow
    }

    val remoteTopHeadlines = try {
      service.getTopHeadlines(category = category)
        .filterNotNull()
        .filterNot {
          it.title?.contains("[Removed]") == true ||
                  it.description?.contains("[Removed]") == true ||
                  it.content?.contains("[Removed]") == true ||
                  it.description == null
        }
    } catch (e: Exception) {
      emit(Resource.Error(message = e.message ?: "Unexpected error"))
      null
    }

    remoteTopHeadlines?.let { topHeadlines ->
      localDataSource.clearTopHeadlinesByCategory(category)
      localDataSource.insertTopHeadlines(topHeadlines.map { it.toEntity(category) })
      val localHeadlines = localDataSource.searchTopHeadlines(
        query = "",
        category = category
      ).map { it.toModel() }
      emit(Resource.Success(data = localHeadlines))
    }
    emit(Resource.Loading(false))
  }

  override fun getArticleById(articleId: String): Flow<Resource<ArticleModel?>> = flow {
    emit(Resource.Loading(true))
    try {
      val article = localDataSource.getTopHeadlineById(articleId)?.toModel()
      emit(Resource.Loading(false))
      emit(Resource.Success(data = article))
    } catch (e: Exception) {
      emit(Resource.Loading(false))
      emit(Resource.Error(message = e.message ?: "Unexpected error"))
    }
  }
}