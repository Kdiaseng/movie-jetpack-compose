package com.example.moviecomposeapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecomposeapp.data.bookService
import com.example.moviecomposeapp.ui.theme.MovieComposeAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel> {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(bookService) as T
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val movies = listOf(
                        Movie(
                            "https://is4-ssl.mzstatic.com/image/thumb/Video114/v4/c0/2d/58/c02d5833-f421-03b8-e9e7-7e05b5f8ff03/pr_source.lsr/200x200bb.png",
                            "The Vault",
                            "2021-03-26"
                        ),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                        Movie("url", "The Vault", "2021-03-26"),
                    )

                    ListMovie(movies = movies)
                }
            }
        }
    }
}


data class Movie(val image: String, val name: String, val date: String)


@Composable
fun ListMovie(movies: List<Movie>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .background(color = colorResource(id = R.color.white_gray))
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
    ) {
        items(movies) { movie ->
            ItemMovie(movie)
        }
    }
}


@Composable
fun ItemMovie(movie: Movie) {
    Surface(shape = MaterialTheme.shapes.medium, modifier = Modifier
        .clickable {
            Log.e("merda", movie.name)
        }
        .animateContentSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(style = MaterialTheme.typography.h6, text = movie.name)
            Text(
                style = MaterialTheme.typography.subtitle2,
                text = movie.date,
                color = Color.Gray,
                fontSize = 12.sp
            )

        }
    }

}


