package com.example.moviecomposeapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecomposeapp.data.Book
import com.example.moviecomposeapp.data.BookService
import kotlinx.coroutines.launch

class MainViewModel(
    private val bookService: BookService
) : ViewModel() {

    private val _booksLiveData = MutableLiveData<List<Book>>()
    val bookLiveData: LiveData<List<Book>> = _booksLiveData

    fun getBooks() {
        viewModelScope.launch {
            try {
                val booksResponse = bookService.getBooks()
                _booksLiveData.value = booksResponse.results
            } catch (ex: Exception) {
                Log.e("Service Error", ex.toString())
            }
        }
    }
}