package com.dxy.MovieAPPS.data.locale

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
  entities = [FavoriteMovies::class],
  version = 1
)
abstract class FavoriteMoviesDatabase : RoomDatabase(){
  abstract fun getFavoriteMovieDao(): FavoriteMoviesDao

}