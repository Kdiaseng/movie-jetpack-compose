package com.example.moviecomposeapp.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface BookService {
    @GET("/")
    suspend fun getBooks(): ResultBook
}

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://rss.itunes.apple.com/api/v1/us/movies/top-movies/all/50/explicit.json")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()


val bookService: BookService = retrofit.create(BookService::class.java)