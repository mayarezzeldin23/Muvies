package com.example.muvies.di

import androidx.room.Room
import com.example.muvies.database.MoviesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val databaseModule = module {
    single<MoviesDatabase> {
        Room.databaseBuilder(
            androidContext(),
            MoviesDatabase::class.java,
            "movies_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}