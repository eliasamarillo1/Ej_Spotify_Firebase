package com.example.ejercicio_spotify_firebase.home

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ejercicio_spotify_firebase.model.Artist
import com.example.ejercicio_spotify_firebase.model.Player
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    private val dataBase = Firebase.database
    private var db: FirebaseFirestore = Firebase.firestore

    private val _artists = MutableStateFlow<List<Artist>>(emptyList())
    val artist: StateFlow<List<Artist>> = _artists

    private val _player = MutableStateFlow<Player?>(null)
    val player:StateFlow<Player?> = _player

    init {
//        repeat(20){
//            loadData()
//        }
        getArtists()
        getPlayer()
    }

    private fun getPlayer() {

        viewModelScope.launch {
            collectPlayer().collect{
                val player = it.getValue(Player::class.java)
                _player.value = player
            }
        }
    }

    private fun loadData() {
        val random = (1..10000).random()
        val artist = Artist(
            name = "Random $random",
            description = " Description randomd",
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFUAfyVe3Easiycyh3isP9wDQTYuSmGPsPQvLIJdEYvQ_DsFq5Ez2Nh_QjiS3oZ3B8ZPfK9cZQyIStmQMV1lDPLw"
        )
        db.collection("artists").add(artist)
    }

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

    private fun collectPlayer(): Flow<DataSnapshot> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                trySend(snapshot).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("Elias log", "Error :  ${error.message}")
                close(error.toException())
            }

        }

        val ref = dataBase.getReference("player")
        ref.addValueEventListener(listener)

        awaitClose{
            ref.removeEventListener(listener)
        }

    }

    fun onPlaySlected() {
        if (player.value != null){
            val currentPlayer = _player.value?.copy(play = !_player.value?.play !!)
            val ref = dataBase.reference.child("player")
            ref.setValue(currentPlayer)
        }
    }
}