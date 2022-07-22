package com.dxy.MovieAPPS.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.dxy.MovieAPPS.data.api.MoviesAPI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(private val moviesAPI: MoviesAPI){
  fun getNowPlayingMovies() =
    Pager(
      config = PagingConfig(
        pageSize = 5,
        maxSize = 20,
        enablePlaceholders = false
      ),
      pagingSourceFactory = {MoviesPagingSources(moviesAPI, null)}
    ).liveData

  fun getSearchMovies(query: String) =
    Pager(
      config = PagingConfig(
        pageSize = 5,
        maxSize = 20,
        enablePlaceholders = false
      ),
      pagingSourceFactory = {MoviesPagingSources(moviesAPI, query)}
    ).liveData
}