package at.ac.fhcampuswien.movieapplication.widgets

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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import at.ac.fhcampuswien.movieapplication.models.Movie
import at.ac.fhcampuswien.movieapplication.models.getMovies
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation



@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(
    movie : Movie,
    onItemClick: (String) -> Unit = {}
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
//                Icon(
//                    imageVector = Icons.Default.AccountBox,
//                    contentDescription = "Profile picture",
//                    modifier = Modifier
//                        .width(100.dp)
//                        .height(100.dp)
//                )
                Image(
                    painter = rememberImagePainter(
                        data = movie.images[0],
                        builder = {
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = "Movie poster",
                    modifier = Modifier.size(100.dp).padding(10.dp)
                )
            }
            Column {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Director: " + movie.director)
                Text(text = "Released: " + movie.year)

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
                        Text("Plot: " + movie.plot, Modifier.fillMaxWidth())
                        Divider(startIndent = 5.dp)
                        Text("Genre: " + movie.genre, Modifier.fillMaxWidth())
                        Text("Actors: " + movie.actors, Modifier.fillMaxWidth())
                        Text("Rating: " + movie.rating, Modifier.fillMaxWidth())
                    }
                }
                Icon(imageVector = if (descriptionVisible) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                    contentDescription = "Arrow Down",
                    Modifier.clickable {
                        descriptionVisible = !descriptionVisible
                    }
                )
            }
        }
    }
}

@Composable
fun HorizontalScrollableImageView(movie: Movie = getMovies()[0]){
    LazyRow{
        items(movie.images) { image ->
            Card(
                modifier = Modifier.padding(12.dp).height(200.dp).width(240.dp),
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