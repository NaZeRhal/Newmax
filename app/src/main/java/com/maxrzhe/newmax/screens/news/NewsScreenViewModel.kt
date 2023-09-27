package com.maxrzhe.newmax.screens.news

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.maxrzhe.newmax.data.dummyNews
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class NewsScreenViewModel : ViewModel() {

  private val _state = MutableStateFlow(NewsScreenState())
  val state: StateFlow<NewsScreenState> = _state

  init {
    _state.update {
      it.copy(news = dummyNews.shuffled())
    }
  }

  fun onEvent(event: NewsScreenEvent) {
    when (event) {
      is NewsScreenEvent.OnQueryChanged -> {
        _state.update {
          it.copy(query = event.query)
        }
      }

      is NewsScreenEvent.OnSearch -> {
        _state.update {
          it.copy(
            query = "",
            isSearchBarActive = false,
            searchHistory = it.searchHistory.plus(event.query)
          )
        }
      }

      is NewsScreenEvent.OnSearchBarActiveChanged -> {
        _state.update {
          it.copy(isSearchBarActive = event.isActive)
        }
      }

      is NewsScreenEvent.OnChipSelected -> {
        _state.update {
          println("index = ${event.index}")
          it.copy(selectedChipIndex = event.index)
        }
      }

      is NewsScreenEvent.OnMarkArticle -> {
        val news = state.value.news
        val existingArticle = news.firstOrNull { it.id == event.id }
        existingArticle?.let {
          val updatedArticle = existingArticle.copy(marked = !existingArticle.marked)
          val updateArticles =
            news.filter { it.id != event.id }.plus(updatedArticle).sortedBy { it.id }
          _state.update {
            it.copy(news = updateArticles)
          }
        }
      }
    }
  }
}

sealed interface NewsScreenEvent {
  data class OnQueryChanged(val query: String) : NewsScreenEvent
  data class OnSearchBarActiveChanged(val isActive: Boolean) : NewsScreenEvent
  data class OnSearch(val query: String) : NewsScreenEvent
  data class OnChipSelected(val index: Int) : NewsScreenEvent
  data class OnMarkArticle(val id: Int) : NewsScreenEvent

}

data class NewsScreenState(
  val query: String = "",
  val selectedChipIndex: Int = 0,
  val news: List<Article> = emptyList(),
  val isSearchBarActive: Boolean = false,
  val searchHistory: List<String> = emptyList()
)

data class Article(
  val id: Int,
  val topic: String,
  val title: String,
  val text: String,
  @DrawableRes val image: Int,
  val marked: Boolean = false
)