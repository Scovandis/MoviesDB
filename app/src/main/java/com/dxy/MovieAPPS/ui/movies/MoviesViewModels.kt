package com.dxy.MovieAPPS.ui.movies

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.dxy.MovieAPPS.data.MoviesRepository
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModels @Inject constructor(
  private val repository: MoviesRepository,
 state: SavedStateHandle
) : ViewModel(){

  companion object{
    private const val CURRENT_QUERY = "current_query"
    private const val EMPTY_QUERY = ""
  }

  private val currentQuery = state.getLiveData(CURRENT_QUERY, EMPTY_QUERY)
  val movies = currentQuery.switchMap { query ->
    if (!query.isEmpty()){
      repository.getSearchMovies(query)
    }else{
      repository.getNowPlayingMovies().cachedIn(viewModelScope)
    }
  }

  fun searchMovies(query: String){
    currentQuery.value = query
  }
}