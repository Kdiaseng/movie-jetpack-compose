package com.example.moviecomposeapp

import com.example.moviecomposeapp.data.MovieService

class MainRepository(private val service: MovieService = MovieClient.movieService) {

    suspend fun getMovies() = service.getMovies()
}