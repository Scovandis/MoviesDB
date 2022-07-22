package com.dxy.MovieAPPS.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dxy.MovieAPPS.databinding.MoviesLoadStateFooterBinding

class MoviesLoadStateAdapter (private val retry: () -> Unit) : LoadStateAdapter<MoviesLoadStateAdapter.LoadStateViewHolder>(){
  override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
    val binding = MoviesLoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return LoadStateViewHolder(binding)
  }

  override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
    holder.bind(loadState)
  }

  inner class LoadStateViewHolder(private val binding: MoviesLoadStateFooterBinding) : RecyclerView.ViewHolder(binding.root){

    init {
      binding.btnRetry.setOnClickListener {
        retry.invoke()
      }
    }

    fun bind(loadState: LoadState){
      with(binding){
        progressBar.isVisible = loadState is LoadState.Loading
        btnRetry.isVisible = loadState !is LoadState.Loading
        tvError.isVisible = loadState !is LoadState.Loading
      }
    }
  }
}