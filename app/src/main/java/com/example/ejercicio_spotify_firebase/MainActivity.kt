package com.example.ejercicio_spotify_firebase

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ejercicio_spotify_firebase.ui.theme.Ejercicio_Spotify_FirebaseTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    private lateinit var navHostController: NavHostController
    private lateinit var auth:FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        db = Firebase.firestore

        enableEdgeToEdge()
        setContent {
            navHostController = rememberNavController()
            Ejercicio_Spotify_FirebaseTheme {
                NavigationWrapper(navHostController, auth,db)
            }
        }
    }

    override fun onStart(){
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser!= null){
            Log.i("Elias", "Usuario logueado")
                auth.signOut()
        }
    }
}

