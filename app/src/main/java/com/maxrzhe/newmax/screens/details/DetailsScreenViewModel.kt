package com.maxrzhe.newmax.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.maxrzhe.newmax.data.dummyNews
import com.maxrzhe.newmax.navigation.Destination
import com.maxrzhe.newmax.screens.news.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DetailsScreenViewModel(
  savedStateHandle: SavedStateHandle
) : ViewModel() {

  private val articleId: String = checkNotNull(savedStateHandle[Destination.ARTICLE_ID])

  private val _state = MutableStateFlow(DetailsScreenState())
  val state: StateFlow<DetailsScreenState> = _state.asStateFlow()

  init {
    println(this)

    _state.update { currentState ->
      println("articleId = $articleId")
      val art = dummyNews.firstOrNull { article ->
        article.id == articleId.toInt()
      }
      println(art)
      currentState.copy(article = art)
    }
  }
}

data class DetailsScreenState(
  val article: Article? = null
)

