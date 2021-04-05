package com.example.moviecomposeapp.data

import retrofit2.http.GET

interface MovieService {
    @GET("movies/top-movies/all/50/explicit.json")
    suspend fun getMovies(): Response
}


//val movieService: MovieService = retrofit.create(MovieService::class.java)