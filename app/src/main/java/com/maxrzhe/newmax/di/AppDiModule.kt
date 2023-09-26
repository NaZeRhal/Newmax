package com.maxrzhe.newmax.di

import com.maxrzhe.newmax.screens.bookmarks.BookmarksViewModel
import com.maxrzhe.newmax.screens.categories.CategoriesViewModel
import com.maxrzhe.newmax.screens.news.NewsScreenViewModel
import com.maxrzhe.newmax.screens.topics.TopicsSelectionViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appDiModule = module {
  viewModelOf(::TopicsSelectionViewModel)
  viewModelOf(::NewsScreenViewModel)
  viewModelOf(::CategoriesViewModel)
  viewModelOf(::BookmarksViewModel)
}