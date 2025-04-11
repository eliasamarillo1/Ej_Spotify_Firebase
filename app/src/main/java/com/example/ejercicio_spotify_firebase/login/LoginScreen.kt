package com.example.ejercicio_spotify_firebase.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aristidevs.cursofirebaselite.ui.theme.Black
import com.aristidevs.cursofirebaselite.ui.theme.SelectedField
import com.aristidevs.cursofirebaselite.ui.theme.UnselectedField
import com.example.ejercicio_spotify_firebase.R
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(auth: FirebaseAuth, navigateToHome: () -> Unit) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .padding(horizontal = 32.dp)
    ) {
        Row(){
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "",
                tint = White
            )
            Spacer(modifier = Modifier.weight(1f))
        }


        Text("Email", color = White, fontWeight = FontWeight.Bold, fontSize = 40.sp)
        TextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(Modifier.height(48.dp))
        Text("Password", color = White, fontWeight = FontWeight.Bold, fontSize = 40.sp)
        TextField(
            value = password, onValueChange = { password = it }, modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(Modifier.height(48.dp))

        Button(onClick = {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{  task->
                if(task.isSuccessful){
                    navigateToHome()
                    Log.i("Elias","LOGIN OK! ")
                }else{
                    Log.i("Elias","LOGIN ERROR! ")
                }
            }
        }) {
            Text(text = "Log in", color = White)
        }



    }

}

