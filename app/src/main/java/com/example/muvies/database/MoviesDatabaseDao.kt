package com.example.muvies.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDatabaseDao {

    @Insert
    suspend fun insert(movie: Movies)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(movie: Movies)

    @Query("SELECT * from movies WHERE id = :key")
    fun getMovie(key: Long): Movies?

    @Query("SELECT * FROM movies ORDER BY id DESC")
    fun getAllMovies(): Flow<List<Movies>>

    @Query("DELETE FROM movies WHERE id = :key")
    suspend fun delete(key: Long)


}