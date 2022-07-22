package com.dxy.MovieAPPS.data.locale

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface FavoriteMoviesDao {
  @Insert
  suspend fun addToFavorite(favoriteMovie: FavoriteMovies)

  @Query("SELECT * FROM favorite_movie")
  fun getFavoriteMovie(): LiveData<List<FavoriteMovies>>

  @Query("SELECT count(*) FROM favorite_movie WHERE favorite_movie.id_movie = :id")
  suspend fun checkMovie(id: String): Int

  @Query("DELETE FROM favorite_movie WHERE favorite_movie.id_movie = :id" )
  suspend fun removeFromFavorite(id: String) : Int
}