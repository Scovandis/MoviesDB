package com.dxy.MovieAPPS.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dxy.MovieAPPS.R
import com.dxy.MovieAPPS.data.locale.FavoriteMovies
import com.dxy.MovieAPPS.data.model.Movies
import com.dxy.MovieAPPS.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite){
  private val viewModel by viewModels<FavoriteViewModels>()
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = FragmentFavoriteBinding.bind(view)

    val adapter = FavoriteAdapter()

    viewModel.movies.observe(viewLifecycleOwner){
      adapter.setMovieList(it)
      binding.apply {
        rvMovie.setHasFixedSize(true)
        rvMovie.adapter = adapter
      }
    }


    adapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback{
      override fun onItemClick(favoriteMovie: FavoriteMovies) {
        val movie = Movies(
          favoriteMovie.id_movie,
          favoriteMovie.overview,
          favoriteMovie.poster_path,
          favoriteMovie.original_title
        )
        val action = FavoriteFragmentDirections.actionNavFavoriteToNavDetails(movie)
        findNavController().navigate(action)
      }

    })
  }
}