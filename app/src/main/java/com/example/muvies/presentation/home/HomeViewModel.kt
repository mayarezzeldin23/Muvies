package com.example.muvies.presentation.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muvies.database.Movies
import com.example.muvies.database.MoviesDatabase
import com.example.muvies.database.MoviesDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(val database: MoviesDatabase) : ViewModel() {


    private var viewModelJob = Job()
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()

    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var _moviesLiveData: MutableLiveData<List<Movies>> = MutableLiveData(emptyList())
    val moviesLiveData: LiveData<List<Movies>> = _moviesLiveData
    private var _movieItemLiveData=  MutableSharedFlow<Movies>()
    val movieItemLiveData: SharedFlow<Movies> = _movieItemLiveData

    init {
        initializeMovies()
    }

    private fun initializeMovies() {
        uiScope.launch {
            //   Log.i("initializeMovies",  getMoviesFromDatabase().value.toString())
            getMoviesFromDatabase().collectLatest {
                _moviesLiveData.value = it
                Log.i("AllMovies", it.toString())
            }

        }
    }

    private suspend fun getMoviesFromDatabase(): Flow<List<Movies>> {
        return withContext(Dispatchers.IO) {
            database.moviesDatabaseDao.getAllMovies()

        }
    }

    fun onAddMovie(movie: Movies) {
        uiScope.launch {

            insert(movie)

        }
    }

    private suspend fun insert(movie: Movies) {
        withContext(Dispatchers.IO) {
            database.moviesDatabaseDao.insert(movie)
        }
    }

    fun onDeleteMovie(movie: Movies) {
        uiScope.launch {
            delete(movie)
        }

    }

    private suspend fun delete(movie: Movies) {
        withContext(Dispatchers.IO) {
            database.moviesDatabaseDao.delete(movie.id)
        }

    }

    fun onEditMovie(movie: Movies) {
        uiScope.launch {
            update(movie)
            Log.i("updateeee", "updateeee ")
        }
    }


    private suspend fun update(movie: Movies) {
        withContext(Dispatchers.IO) {
            database.moviesDatabaseDao.update(movie)
        }

    }


    fun getMovieItem(movie: Movies) {
        viewModelScope.launch(Dispatchers.IO) {
            database.moviesDatabaseDao.getMovie(movie.id)?.let { _movieItemLiveData.emit(it) }
        }
    }


}