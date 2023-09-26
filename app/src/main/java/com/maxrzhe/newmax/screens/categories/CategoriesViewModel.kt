package com.maxrzhe.newmax.screens.categories

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CategoriesViewModel : ViewModel() {

  private val _state = MutableStateFlow(CategoriesState())
  val state: StateFlow<CategoriesState> = _state.asStateFlow()

  fun onItemSelected(index: Int) {
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


data class CategoriesState(
  val selectedItems: Set<Int> = emptySet()
)