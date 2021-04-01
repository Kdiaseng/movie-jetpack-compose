package com.example.moviecomposeapp.data


class ResultBook(val results: List<Book>)

class Book(
    val id: String,
    val artistName: String,
    val releaseDate: String,
    val kind: String,
    val artworkUrl100: String
)