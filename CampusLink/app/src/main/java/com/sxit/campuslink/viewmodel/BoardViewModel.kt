package com.sxit.campuslink.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sxit.campuslink.model.Board
import com.sxit.campuslink.network.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BoardViewModel : ViewModel() {
    private val _boards = MutableStateFlow<List<Board>>(emptyList())
    val boards: StateFlow<List<Board>> = _boards

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchBoards() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val result = ApiClient.apiService.getBoards()
                _boards.value = result
            } catch (e: Exception) {
                e.printStackTrace()
                _error.value = e.localizedMessage ?: "请求失败"
            } finally {
                _loading.value = false
            }
        }
    }
}
