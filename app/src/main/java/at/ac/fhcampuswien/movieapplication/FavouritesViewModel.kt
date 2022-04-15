package at.ac.fhcampuswien.movieapplication

import androidx.lifecycle.ViewModel
import at.ac.fhcampuswien.movieapplication.models.Movie

class FavouritesViewModel : ViewModel() {
    private val _favoriteMovies = mutableListOf<Movie>()
    val favoriteMovies: List<Movie>
        get() = _favoriteMovies



    fun AddMovieToFavorites(movie: Movie){
        if(!CheckIfMovieIsFavourite(movie)){
            _favoriteMovies.add(movie)
        }
    }
    fun RemoveMovieFromFavourites(movie: Movie){
        _favoriteMovies.remove(movie)
    }
    fun GetAllFavouriteMovies() : List<Movie>{
        return _favoriteMovies
    }
    fun CheckIfMovieIsFavourite(movie: Movie) : Boolean{
        return _favoriteMovies.contains(movie)
    }
}

