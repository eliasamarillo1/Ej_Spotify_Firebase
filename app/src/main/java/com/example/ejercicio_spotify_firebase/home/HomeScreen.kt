package com.example.ejercicio_spotify_firebase.home

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun HomeScreen(db: FirebaseFirestore) {
    Button(onClick = {
        createArtist(db)
    }) {
        Text(text = "Crear artista")
    }

}

data class Artist(
    val name: String,
    val numberOfSongs: Int
)

fun createArtist(db: FirebaseFirestore) {

    val random = (1..10000).random()
    val artist = Artist(name = "Artista $random", numberOfSongs = random)
    db.collection("artists")
        .add(artist)
        .addOnSuccessListener {
            Log.i("Elias", "SUCCES")
        }
        .addOnFailureListener {
            Log.i("Elias", "FAILURE")
        }
        .addOnCompleteListener {
            Log.i("Elias", "COMPLETE")
        }

}