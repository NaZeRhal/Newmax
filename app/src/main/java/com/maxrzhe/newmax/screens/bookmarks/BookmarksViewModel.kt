package com.maxrzhe.newmax.screens.bookmarks

import androidx.lifecycle.ViewModel
import com.maxrzhe.newmax.data.dummyNews
import com.maxrzhe.newmax.screens.news.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookmarksViewModel : ViewModel() {

  private val _state = MutableStateFlow(BookmarksState())
  val state: StateFlow<BookmarksState> = _state.asStateFlow()

  init {
    _state.update {
      it.copy(savedNews = dummyNews)
    }
  }
}

data class BookmarksState(
  val savedNews: List<Article> = emptyList()
)