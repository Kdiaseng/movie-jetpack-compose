package com.example.moviecomposeapp.data


class ResultMovie(val results: List<Movie>)

class Movie(
    val id: String,
    val artistName: String,
    val releaseDate: String,
    val kind: String,
    val artworkUrl100: String,
    val name: String
)