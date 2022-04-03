package at.ac.fhcampuswien.movieapplication.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import at.ac.fhcampuswien.movieapplication.models.getMovies
import at.ac.fhcampuswien.movieapplication.widgets.MovieRow

@Composable
fun FavouriteScreen(navController: NavController){
    var movieList = getMovies()
    var favouriteList = listOf(
        movieList[0],
        movieList[2],
        movieList[4]
    )
    MainContent(navController = navController){
        LazyColumn{
            items(favouriteList) { movie ->
                //OnClick for our MovieCard
                MovieRow(movie = movie)
            }
        }
    }
}

@Composable
fun MainContent(navController: NavController, content: @Composable () -> Unit){
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
                    Text(text = "My Favourite Movies")
                }
            }
        }
    ) {
        content()
    }
}