package com.example.muvies.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "movies")
data class Movies(

    @ColumnInfo(name = "title")
    val title: String = "Movie Title",
    @ColumnInfo(name = "overview")
    val overview: String = "Movie Overview",
    @ColumnInfo(name = "poster_path")
    val poster_path: String,
    @ColumnInfo(name = "trailer_path")
    val trailer_path: String,
    @ColumnInfo(name = "release_date")
    val release_date: String = "",
    @ColumnInfo(name = "rate")
    val rate: Double = 0.0,
    @ColumnInfo(name = "star")
    val star: String = "",
    @ColumnInfo(name = "isPopular")
    val isPopular: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L

): Serializable

