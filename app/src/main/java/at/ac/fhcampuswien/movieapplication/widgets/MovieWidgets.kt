package at.ac.fhcampuswien.movieapplication.widgets

import android.util.Log.d
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import at.ac.fhcampuswien.movieapplication.models.Movie
import at.ac.fhcampuswien.movieapplication.models.getMovies
import at.ac.fhcampuswien.movieapplication.ui.theme.Teal200
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation



@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(
    movie : Movie,
    isFavourite: Boolean,
    showFavIcon: Boolean,
    onItemClick: (String) -> Unit = {},
    onFavouriteIconClick: () -> Unit = {}
) {
    var descriptionVisible by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(5.dp)
            .height(if (!descriptionVisible) 130.dp else 400.dp)
            .clickable {
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .padding(15.dp, 5.dp, 5.dp, 5.dp),
                elevation = 4.dp,
                shape = RoundedCornerShape(corner = CornerSize(5.dp))
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = movie.images[0],
                        builder = {
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = "Movie poster",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(10.dp)
                )
            }
            Column(modifier = Modifier.weight(30f)){
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Director: " + movie.director, style = MaterialTheme.typography.subtitle1)
                Text(text = "Released: " + movie.year, style = MaterialTheme.typography.subtitle1)

                AnimatedVisibility(
                    visible = descriptionVisible,
                    enter = expandVertically(
                        // Expand from the top.
                        expandFrom = Alignment.Top,
                        animationSpec = spring(3f)
                    ),
                    exit = shrinkVertically()
                ) {
                    Column(modifier = Modifier.padding(5.dp)) {
                        Text("Plot: " + movie.plot, Modifier.fillMaxWidth(), style = MaterialTheme.typography.body1)
                        Divider(startIndent = 5.dp)
                        Text("Genre: " + movie.genre, Modifier.fillMaxWidth(), style = MaterialTheme.typography.body1)
                        Text("Actors: " + movie.actors, Modifier.fillMaxWidth(), style = MaterialTheme.typography.body1)
                        Text("Rating: " + movie.rating, Modifier.fillMaxWidth(), style = MaterialTheme.typography.body1)
                    }
                }
                Icon(imageVector = if (descriptionVisible) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                    contentDescription = "Arrow Down",
                    Modifier.clickable {
                        descriptionVisible = !descriptionVisible
                    }
                )
            }
            if(showFavIcon) {
                FavouriteIcon(
                    modifier = Modifier.weight(10f),
                    isFavourite = isFavourite,
                    onFavouriteIconClick = onFavouriteIconClick
                )
            }
        }
    }
}

@Composable
fun FavouriteIcon(
    modifier: Modifier,
    isFavourite: Boolean,
    onFavouriteIconClick: () -> Unit = {}
){
    IconButton(
        modifier = modifier,
        onClick = { onFavouriteIconClick() }) {
        Icon(
            contentDescription = "Favourite Icon",
            tint = Teal200,
            imageVector =
            if (isFavourite) {
                Icons.Default.Favorite
            } else {
                Icons.Default.FavoriteBorder
            }
        )
    }
}

@Composable
fun HorizontalScrollableImageView(movie: Movie = getMovies()[0]){
    LazyRow{
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .height(200.dp)
                    .width(240.dp),
                elevation = 4.dp
            ) {
                Image(
                    painter = rememberImagePainter(data = image),
                    contentDescription = "movie image"
                )
            }
        }
    }
}
