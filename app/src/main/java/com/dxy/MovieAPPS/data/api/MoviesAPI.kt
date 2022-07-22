package com.dxy.MovieAPPS.data.api

import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesAPI {
  companion object{
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "44dd891d5199be79d3fe8b216b752a4d"
  }
  @GET("movie/now_playing?api_key=$API_KEY")
  suspend fun getNowPlayingMovies(
    @Query("page") position: Int
  ): MoviesResponse

  @GET("search/movie?api_key=$API_KEY")
  suspend fun searchMovies(
    @Query("query") query: String,
    @Query("page") page: Int
  ): MoviesResponse
}