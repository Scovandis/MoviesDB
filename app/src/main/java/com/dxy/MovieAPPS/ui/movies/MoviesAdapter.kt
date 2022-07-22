package com.dxy.MovieAPPS.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dxy.MovieAPPS.R
import com.dxy.MovieAPPS.data.model.Movies
import com.dxy.MovieAPPS.databinding.ItemMoviesBinding

class MoviesAdapter (private val listener: OnItemClickListener): PagingDataAdapter<Movies, MoviesAdapter.MoviesViewHolder>(
  COMPARATOR) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
    val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return MoviesViewHolder(binding)
  }

  override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
    val currentItem = getItem(position)
    if (currentItem != null) {
      holder.bind(currentItem)
    }
  }

  inner class MoviesViewHolder(private val binding: ItemMoviesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
      binding.root.setOnClickListener {
        val position = bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION){
          val item = getItem(position)
          if (item!=null){
            listener.onItemClick(item)
          }
        }
      }
    }

    fun bind(movie: Movies) {
      with(binding) {
        Glide.with(itemView)
          .load("${movie.baseUrl}${movie.poster_path}")
          .centerCrop()
          .transition(DrawableTransitionOptions.withCrossFade())
          .error(R.drawable.ic_error)
          .into(ivMoviePoster)
        tvMovieTitle.text = movie.original_title
      }
    }
  }

  interface OnItemClickListener{
    fun onItemClick(movie: Movies)
  }
  companion object {
    private val COMPARATOR = object : DiffUtil.ItemCallback<Movies>() {
      override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean =
        oldItem.id == newItem.id

      override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean =
        oldItem == newItem
    }
  }

}

