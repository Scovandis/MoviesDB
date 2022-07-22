package com.dxy.MovieAPPS.ui.favorite

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dxy.MovieAPPS.R
import com.dxy.MovieAPPS.data.locale.FavoriteMovies
import com.dxy.MovieAPPS.databinding.ItemMoviesBinding

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>(){
  private lateinit var list : List<FavoriteMovies>

  private var onItemClickCallback: OnItemClickCallback? = null

  fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
    this.onItemClickCallback = onItemClickCallback
  }

  fun setMovieList(list: List<FavoriteMovies>){
    this.list = list
    notifyDataSetChanged()
  }

  inner class FavoriteViewHolder(private val binding: ItemMoviesBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(favoriteMovie: FavoriteMovies) {
      with(binding) {

        Glide.with(itemView)
          .load("${favoriteMovie.baseUrl}${favoriteMovie.poster_path}")
          .centerCrop()
          .transition(DrawableTransitionOptions.withCrossFade())
          .error(R.drawable.ic_error)
          .into(ivMoviePoster)
        tvMovieTitle.text = favoriteMovie.original_title
        binding.root.setOnClickListener { onItemClickCallback?.onItemClick(favoriteMovie) }
      }
    }

  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
    val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return FavoriteViewHolder(binding)
  }

  override fun getItemCount(): Int = list.size

  override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
    Log.e("adapter", "Masuk bind view holder")
    holder.bind(list[position])
  }

  interface OnItemClickCallback {
    fun onItemClick(favoriteMovie: FavoriteMovies)
  }
}