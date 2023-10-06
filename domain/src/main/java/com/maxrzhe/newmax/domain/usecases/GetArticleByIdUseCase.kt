package com.maxrzhe.newmax.domain.usecases

import com.maxrzhe.newmax.common.Resource
import com.maxrzhe.newmax.core.models.ArticleModel
import com.maxrzhe.newmax.data_api.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetArticleByIdUseCase(private val repository: NewsRepository) {

  fun execute(articleId: String): Flow<Resource<ArticleModel?>> = repository.getArticleById(
    articleId = articleId
  )
}