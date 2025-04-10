package com.example.ejercicio_spotify_firebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ejercicio_spotify_firebase.ui.theme.Ejercicio_Spotify_FirebaseTheme

class MainActivity : ComponentActivity() {
    private lateinit var navHostController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            navHostController = rememberNavController()

            Ejercicio_Spotify_FirebaseTheme {
                NavigationWrapper(navHostController)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier.clickable {
            throw RuntimeException("Crashhh")
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ejercicio_Spotify_FirebaseTheme {
        Greeting("Android")
    }
}