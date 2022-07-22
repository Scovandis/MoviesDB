package com.dxy.MovieAPPS.data

import androidx.paging.PagingSource
import com.dxy.MovieAPPS.data.api.MoviesAPI
import com.dxy.MovieAPPS.data.model.Movies
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class MoviesPagingSources (private val movieApi: MoviesAPI,
                           private val query: String?): PagingSource<Int, Movies>(){
  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movies> {
    return try {
      val position = params.key ?: STARTING_PAGE_INDEX
      val response = if (query!=null) movieApi.searchMovies(query,position) else movieApi.getNowPlayingMovies(position)
      val movies = response.result

      LoadResult.Page(
        data = movies,
        prevKey = if (position == STARTING_PAGE_INDEX) null else position-1,
        nextKey = if (movies.isEmpty()) null else position+1
      )
    } catch (e: IOException){
      LoadResult.Error(e)
    } catch (e: HttpException){
      LoadResult.Error(e)
    }
  }
}