package com.assignment.diagnal.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.diagnal.models.MoviesResponse
import com.assignment.diagnal.repositories.MoviesRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(private val repo: MoviesRepository) : ViewModel() {
    val liveDataObserver = MutableLiveData<MoviesState>()

    fun getMoviesList(currentPage: Int) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val res = repo.getMoviesList(currentPage = currentPage)
            withContext(Dispatchers.Main) {
                liveDataObserver.value = MoviesState.Success(res)
            }
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    private fun onError(message: String) {
        liveDataObserver.postValue(MoviesState.Loading(false))
        liveDataObserver.postValue(MoviesState.Error(message))
    }
}

sealed class MoviesState {
    data class Loading(val isLoading: Boolean) : MoviesState()
    data class Success(val moviesResponse: MoviesResponse) : MoviesState()
    data class Error(val error: String) : MoviesState()
}