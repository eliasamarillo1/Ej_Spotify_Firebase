package com.example.ejercicio_spotify_firebase

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ejercicio_spotify_firebase.home.HomeScreen
import com.example.ejercicio_spotify_firebase.initial.InitialScreen
import com.example.ejercicio_spotify_firebase.login.LoginScreen
import com.example.ejercicio_spotify_firebase.signup.SignUpScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable

fun NavigationWrapper(
    navHostController: NavHostController,
    auth: FirebaseAuth,
    db: FirebaseFirestore
) {
    NavHost(navController = navHostController, startDestination = "initial") {

        composable("initial") {
            InitialScreen(
                navigateToLoin = { navHostController.navigate("logIn") },
                navegateToSignUp = { navHostController.navigate("signup") }
            )
        }
        composable("logIn") {
            LoginScreen(auth){navHostController.navigate("home")}
        }
        composable("signup") {
            SignUpScreen(auth)
        }
        composable("home") {
            HomeScreen(db)
        }
    }
}