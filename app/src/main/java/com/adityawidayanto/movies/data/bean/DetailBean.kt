package com.adityawidayanto.movies.data.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailBean(
    val id: Int,
    val title: String,
    val poster: String,
    val overview: String,
    val releaseDate: String,
    val backDrop: String,
    val popularity: Double
) : Parcelable
