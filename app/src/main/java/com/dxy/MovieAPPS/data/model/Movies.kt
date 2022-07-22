package com.dxy.MovieAPPS.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies(
  val id: String,
  val overview : String?,
  val poster_path: String,
  val original_title: String
)   : Parcelable {
  val baseUrl get() = "https://image.tmdb.org/t/p/w500"
}
