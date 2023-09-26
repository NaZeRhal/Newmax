package com.maxrzhe.newmax.screens.topics

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TopicsSelectionViewModel : ViewModel() {

  private val _state = MutableStateFlow(TopicsSelectionState())
  val state: StateFlow<TopicsSelectionState> = _state.asStateFlow()


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