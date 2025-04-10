package com.example.ejercicio_spotify_firebase

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ejercicio_spotify_firebase.initial.InitialScreen
import com.example.ejercicio_spotify_firebase.login.LoginScreen
import com.example.ejercicio_spotify_firebase.signup.SignUpScreen

@Composable

fun NavigationWrapper(navHostController:NavHostController){
    NavHost(navController = navHostController, startDestination = "inital"){

        composable("initial"){
            InitialScreen()
        }
        composable("logIn"){
            LoginScreen()
        }
        composable("signup"){
            SignUpScreen()
        }
    }
}