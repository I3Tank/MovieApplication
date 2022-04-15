package at.ac.fhcampuswien.movieapplication

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.compose.ui.tooling.preview.Preview
import at.ac.fhcampuswien.movieapplication.ui.theme.MovieApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MovieNavigation()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit){
    MovieApplicationTheme {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    TopAppBar {
        MovieNavigation()
    }
}

