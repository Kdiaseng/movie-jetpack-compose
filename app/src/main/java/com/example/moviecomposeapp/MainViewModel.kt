package com.example.moviecomposeapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecomposeapp.data.Movie
import com.example.moviecomposeapp.data.MovieService
import kotlinx.coroutines.launch

class MainViewModel(
    private val movieService: MovieService
) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val movieLiveData: LiveData<List<Movie>> = _moviesLiveData

    fun getMovies() {
        viewModelScope.launch {
            try {
                val moviesResponse = movieService.getMovies()
                _moviesLiveData.value = moviesResponse.results
            } catch (ex: Exception) {
                Log.e("Service Error", ex.toString())
            }
        }
    }
}