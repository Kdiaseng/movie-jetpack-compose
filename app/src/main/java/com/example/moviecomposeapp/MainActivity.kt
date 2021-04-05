package com.example.moviecomposeapp

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.moviecomposeapp.data.Movie
import com.example.moviecomposeapp.ui.theme.MovieComposeAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this, MainFactory(MainRepository()))
            .get(MainViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieComposeAppTheme {
                ListMovie(moviesData = viewModel.movieLiveData)
            }
        }
        viewModel.getMovies()
    }

}


@Composable
fun ListMovie(moviesData: LiveData<List<Movie>>) {
    val movies by moviesData.observeAsState(emptyList())
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

            val imageModifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(4.dp))
            RemoteImage(uri = movie.artworkUrl100, modifier = imageModifier)
            Text(style = MaterialTheme.typography.h6, text = movie.name)
            Text(
                style = MaterialTheme.typography.subtitle2,
                text = movie.releaseDate,
                color = Color.Gray,
                fontSize = 12.sp
            )

        }
    }

}

@Composable
fun RemoteImage(uri: String, modifier: Modifier = Modifier) {
    val bitmapState = remember { mutableStateOf<Bitmap?>(null) }
    Glide.with(LocalContext.current).asBitmap().load(uri).into(
        object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }

        }
    )

    bitmapState.value?.let {
        Image(bitmap = it.asImageBitmap(), contentDescription = "", modifier = modifier)
    }
}


