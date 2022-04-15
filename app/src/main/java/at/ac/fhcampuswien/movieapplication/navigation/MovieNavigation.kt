package at.ac.fhcampuswien.movieapplication

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import at.ac.fhcampuswien.movieapplication.models.getMovies
import at.ac.fhcampuswien.movieapplication.screens.FavouriteScreen

@Composable
fun MovieNavigation(){
    val favouritesViewModel : FavouritesViewModel = viewModel()

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "homescreen"){

        composable("homescreen") { HomeScreen(navController = navController) }
        composable(
            "detailscreen/{movieId}",
            arguments = listOf(navArgument("movieId"){})
            ) { backStackEntry ->
            DetailScreen(navController = navController, movieId = backStackEntry.arguments?.getString("movieId"))
        }
        composable("favouritescreen") { FavouriteScreen(navController = navController, viewModel = favouritesViewModel)}
    }

}