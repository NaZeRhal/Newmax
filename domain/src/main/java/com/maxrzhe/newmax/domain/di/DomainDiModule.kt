package com.maxrzhe.newmax.domain.di

import com.maxrzhe.newmax.domain.usecases.GetArticleByIdUseCase
import com.maxrzhe.newmax.domain.usecases.GetTopHeadlinesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainDiModule = module {
  factoryOf(::GetArticleByIdUseCase)
  factoryOf(::GetTopHeadlinesUseCase)
}