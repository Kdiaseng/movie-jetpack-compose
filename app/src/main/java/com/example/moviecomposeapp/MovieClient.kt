package com.example.moviecomposeapp

import com.example.moviecomposeapp.data.MovieService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface MovieClient {

    companion object {

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://rss.itunes.apple.com/api/v1/us/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val movieService: MovieService by lazy {
            retrofit.create(MovieService::class.java)
        }
    }

}