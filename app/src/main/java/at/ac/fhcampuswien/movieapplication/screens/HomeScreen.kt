package at.ac.fhcampuswien.movieapplication

import android.util.Log
import android.util.Log.d
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import at.ac.fhcampuswien.movieapplication.models.Movie
import at.ac.fhcampuswien.movieapplication.models.getMovies
import at.ac.fhcampuswien.movieapplication.ui.theme.MovieApplicationTheme
import at.ac.fhcampuswien.movieapplication.widgets.MovieRow
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

@Composable
fun HomeScreen(navController: NavController, viewModel: FavouritesViewModel) {
    var showMenu by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Movies") },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false },
                        Modifier.width(150.dp)
                    ) {
                        DropdownMenuItem(onClick = {
                            navController.navigate("favouritescreen")
                        }) {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "my favourites",
                                    modifier = Modifier.padding(4.dp)
                                )
                                Text(text = "Favourites")
                            }
                        }

                    }
                })
            }
        ) {
        MainContent(navController = navController, viewModel = viewModel)
    }
}

@Composable
fun MainContent(navController: NavController,viewModel: FavouritesViewModel, movies: List<Movie> = getMovies()) {

    var isFav by remember{
        mutableStateOf(false)
    }

    LazyColumn {
        items(movies) { movie ->
            isFav = viewModel.CheckIfMovieIsFavourite(movie)
            MovieRow(
                movie = movie,
                isFavourite = isFav,
                showFavIcon = true,
                //OnClick for our MovieCard
                onItemClick = { movieId ->
                    navController.navigate("detailscreen/$movieId")
                },
                //OnClick for the Favourite Icon
                onFavouriteIconClick = { movie ->
                    isFav = viewModel.CheckIfMovieIsFavourite(movie)
                    if(isFav){
                        viewModel.RemoveMovieFromFavourites(movie)
                        isFav = false
                        d("FAVOOO", "remove")
                    }
                    else{
                        viewModel.AddMovieToFavorites(movie)
                        isFav = true
                        d("FAVOOO", "add")
                    }
                }
            )
        }
    }
}


