package me.yihtseu.mikuyou.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class SearchState(
    val term: String = ""
)

class SearchModel : ViewModel() {
    private val localState = MutableStateFlow(SearchState())
    val state = localState.asStateFlow()
}