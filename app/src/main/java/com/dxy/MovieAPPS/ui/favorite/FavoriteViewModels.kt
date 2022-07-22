package com.dxy.MovieAPPS.ui.favorite

import androidx.lifecycle.ViewModel
import com.dxy.MovieAPPS.data.locale.FavoriteMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModels @Inject constructor(private val repository: FavoriteMoviesRepository): ViewModel(){
  val movies = repository.getFavoriteMovies()
}