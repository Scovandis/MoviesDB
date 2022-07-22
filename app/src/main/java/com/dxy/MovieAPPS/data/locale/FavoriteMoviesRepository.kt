package com.dxy.MovieAPPS.data.locale

import javax.inject.Inject

class FavoriteMoviesRepository @Inject constructor(private val favoriteMoviesDao: FavoriteMoviesDao){
  suspend fun addToFavorite(favoriteMovie: FavoriteMovies) = favoriteMoviesDao.addToFavorite(favoriteMovie)
  fun getFavoriteMovies() = favoriteMoviesDao.getFavoriteMovie()
  suspend fun checkMovie(id: String) = favoriteMoviesDao.checkMovie(id)
  suspend fun removeFromFavorite(id: String) {
    favoriteMoviesDao.removeFromFavorite(id)
  }
}