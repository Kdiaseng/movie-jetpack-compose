package com.example.moviecomposeapp.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface MovieService {
    @GET("/")
    suspend fun getMovies(): ResultMovie
}

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://rss.itunes.apple.com/api/v1/us/movies/top-movies/all/50/explicit.json")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()


val movieService: MovieService = retrofit.create(MovieService::class.java)