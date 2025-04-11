package com.example.ejercicio_spotify_firebase.home

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ejercicio_spotify_firebase.model.Artist
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    private var db: FirebaseFirestore = Firebase.firestore

    private val _artists = MutableStateFlow<List<Artist>>(emptyList())
    val artist: StateFlow<List<Artist>> = _artists

    init {
//        repeat(20){
//            loadData()
//        }
        getArtists()
    }

//    private fun loadData() {
//        val random = (1..10000).random()
//        val artist = Artist(
//            name = "Random $random",
//            description = " Description randomd",
//            image = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.outsideonline.com%2Fculture%2Factive-families%2Fhow-to-read-dog-body-language-happy-aggressive%2F&psig=AOvVaw2_iGet7J5UpGsdV7OFWQi5&ust=1744456286383000&source=images&opi=89978449"
//        )
//        db.collection("artists").add(artist)
//    }

    private fun getArtists() {
        viewModelScope.launch {
            val result: List<Artist> = withContext(Dispatchers.IO) {
                getAllArtists()
            }
            _artists.value = result
        }
    }

    private suspend fun getAllArtists(): List<Artist> {
        return try {
            db.collection("artists")
                .get()
                .await()
                .documents
                .mapNotNull { doc ->
                    doc.toObject(Artist::class.java)
                }
        } catch (e: Exception) {
            Log.i("aris", e.toString())
            emptyList()
        }

    }
}