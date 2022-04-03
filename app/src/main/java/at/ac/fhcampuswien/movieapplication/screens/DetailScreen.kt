package at.ac.fhcampuswien.movieapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import at.ac.fhcampuswien.movieapplication.models.Movie
import at.ac.fhcampuswien.movieapplication.models.getMovies
import at.ac.fhcampuswien.movieapplication.widgets.HorizontalScrollableImageView
import at.ac.fhcampuswien.movieapplication.widgets.MovieRow

@Composable
fun DetailScreen(navController: NavController, movieId: String?){
    val movie = FilterMovie(movieId = movieId)
    MainContent(movie.title, navController){
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Column {
                MovieRow(movie = movie)
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Text(text = "Movie Images", style = MaterialTheme.typography.h5, modifier = Modifier.align(
                    Alignment.CenterHorizontally))
                HorizontalScrollableImageView(movie = movie)
            }
        }
    }
}

@Composable
fun MainContent(movieTitle: String, navController: NavController, content: @Composable () -> Unit){
    Scaffold(
        topBar =  {
            TopAppBar(elevation = 3.dp) {
                Row {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow back",
                        modifier = Modifier.clickable {
                            //Go back to the last destination
                            navController.popBackStack()
                        })
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = movieTitle)
                }
            }
        }
    ) {
        content()
    }
}

fun FilterMovie(movieId: String?): Movie {
    return getMovies().filter { movie -> movie.id == movieId }[0]
}