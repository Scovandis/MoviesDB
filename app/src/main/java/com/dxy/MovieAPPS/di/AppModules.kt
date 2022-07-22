package com.dxy.MovieAPPS.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dxy.MovieAPPS.data.api.MoviesAPI
import com.dxy.MovieAPPS.data.locale.FavoriteMoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModules {

  @Singleton
  @Provides
  fun provideFavMoviesDatabase(
    @ApplicationContext app: Context
  )= Room.databaseBuilder(
    app,
    FavoriteMoviesDatabase::class.java,
    "movies_db")
    .build()

  @Singleton
  @Provides
  fun provideFavMoviesDAO(db: FavoriteMoviesDatabase) = db.getFavoriteMovieDao()

  @Provides
  @Singleton
  fun provideRetrofit(): Retrofit =
    Retrofit.Builder()
      .baseUrl(MoviesAPI.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()

  @Provides
  @Singleton
  fun provideMoviesAPI(retrofit: Retrofit): MoviesAPI = retrofit.create(MoviesAPI::class.java)


}