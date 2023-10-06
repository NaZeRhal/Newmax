package com.maxrzhe.newmax.screens.topics

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxrzhe.newmax.common.Constants.TAG
import com.maxrzhe.newmax.common.Resource
import com.maxrzhe.newmax.core.models.NewsCategory
import com.maxrzhe.newmax.domain.usecases.GetTopHeadlinesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TopicsSelectionViewModel(
  private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
) : ViewModel() {

  private val _state = MutableStateFlow(TopicsSelectionState())
  val state: StateFlow<TopicsSelectionState> = _state.asStateFlow()


  init {
    viewModelScope.launch {
      getTopHeadlinesUseCase.execute(
        shouldFetchFromRemote = true,
        category = NewsCategory.GENERAL.name.lowercase(),
        query = ""
      ).collectLatest { resource ->
        when (resource) {
          is Resource.Error -> {
            Log.e(TAG, "Error: ${resource.message}")
          }

          is Resource.Loading -> {
            Log.w(TAG, "LOADING.....:")
          }

          is Resource.Success -> {
            Log.w(TAG, "Success:")
            resource.data?.forEach {
              Log.w(TAG, "${it.author} >>> ${it.title}")
            }
          }
        }
      }
    }
  }

  fun onEvent(event: TopicsSelectionEvent) {
    when (event) {
      is TopicsSelectionEvent.OnSelectionChanged -> {
        val index = event.index
        val selectedItems = state.value.selectedItems
        val selected = selectedItems.contains(index)

        val updatedItems = if (selected) {
          selectedItems.minus(index)
        } else {
          selectedItems.plus(index)
        }

        _state.update {
          it.copy(selectedItems = updatedItems)
        }
      }
    }
  }
}

sealed interface TopicsSelectionEvent {
  data class OnSelectionChanged(val index: Int) : TopicsSelectionEvent
}


data class TopicsSelectionState(
  val selectedItems: Set<Int> = emptySet()
)