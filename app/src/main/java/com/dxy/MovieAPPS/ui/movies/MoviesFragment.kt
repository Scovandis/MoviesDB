package com.dxy.MovieAPPS.ui.movies

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.dxy.MovieAPPS.R
import com.dxy.MovieAPPS.data.model.Movies
import com.dxy.MovieAPPS.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(R.layout.fragment_movies), MoviesAdapter.OnItemClickListener{
  private val viewModel by viewModels<MoviesViewModels>()
  private var _binding : FragmentMoviesBinding? = null
  private val binding get() = _binding!!

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    _binding = FragmentMoviesBinding.bind(view)

    val adapter = MoviesAdapter(this)

    binding.apply {
      rvMovie.setHasFixedSize(true)
      rvMovie.adapter = adapter.withLoadStateHeaderAndFooter(
        header = MoviesLoadStateAdapter {adapter.retry()},
        footer = MoviesLoadStateAdapter {adapter.retry()}
      )
      btnTryAgain.setOnClickListener {
        adapter.retry()
      }
    }

    viewModel.movies.observe(viewLifecycleOwner){
      adapter.submitData(viewLifecycleOwner.lifecycle, it)
    }

    adapter.addLoadStateListener { loadState ->
      binding.apply {
        progressBar.isVisible = loadState.source.refresh is LoadState.Loading
        rvMovie.isVisible = loadState.source.refresh is LoadState.NotLoading
        btnTryAgain.isVisible =loadState.source.refresh is LoadState.Error
        tvFailed.isVisible = loadState.source.refresh is LoadState.Error

        //not found
        if (loadState.source.refresh is LoadState.NotLoading &&
          loadState.append.endOfPaginationReached &&
          adapter.itemCount < 1){
          rvMovie.isVisible = false
          tvNotFound.isVisible = true
        } else {
          tvNotFound.isVisible = false
        }
      }
    }

    setHasOptionsMenu(true)
  }

  override fun onItemClick(movie: Movies) {
    val action = MoviesFragmentDirections.actionNavMovieToNavDetails(movie)
    findNavController().navigate(action)
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    inflater.inflate(R.menu.menu_search, menu)

    val searchItem = menu.findItem(R.id.action_search)
    val searchView = searchItem.actionView as SearchView

    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
      override fun onQueryTextSubmit(query: String?): Boolean {
        if (query!=null){
          binding.rvMovie.scrollToPosition(0)
          viewModel.searchMovies(query)
          searchView.clearFocus()
        }
        return true
      }

      override fun onQueryTextChange(newText: String?): Boolean {
        return true
      }

    })
  }
}