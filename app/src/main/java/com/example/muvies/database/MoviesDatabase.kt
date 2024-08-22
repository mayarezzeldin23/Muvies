package com.example.muvies.database

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movies::class], version = 2, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract val moviesDatabaseDao: MoviesDatabaseDao



}