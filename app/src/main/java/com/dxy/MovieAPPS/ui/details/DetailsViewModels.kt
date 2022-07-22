package com.dxy.MovieAPPS.ui.details

import androidx.lifecycle.ViewModel
import com.dxy.MovieAPPS.data.locale.FavoriteMovies
import com.dxy.MovieAPPS.data.locale.FavoriteMoviesRepository
import com.dxy.MovieAPPS.data.model.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModels @Inject constructor(private val repository : FavoriteMoviesRepository): ViewModel(){
  fun addToFavorite(movies: Movies){
    CoroutineScope(Dispatchers.IO).launch {
      repository.addToFavorite(
        FavoriteMovies(
          movies.id,
          movies.original_title,
          movies.overview,
          movies.poster_path
        )
      )
    }
  }
  suspend fun checkMovies(id: String) = repository.checkMovie(id)

  fun removeFromFavorite(id: String){
    CoroutineScope(Dispatchers.IO).launch {
      repository.removeFromFavorite(id)
    }
  }
}