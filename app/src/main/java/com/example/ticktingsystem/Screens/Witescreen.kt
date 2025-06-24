package com.example.ticktingsystem.Screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ticktingsystem.DataModel.scamData
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.jvm.java

@Composable
fun WhiteScreen(navController: NavController) {
    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current

    var data by remember { mutableStateOf<scamData?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        db.collection("users").document("6zvDfgzaTiPlq7FBISge")
            .get()
            .addOnSuccessListener {
                val fetchedData = it.toObject(scamData::class.java)
                data = fetchedData
                isLoading = false
                if (fetchedData?.textBased == "") {
                    navController.navigate("login_screen") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }
            .addOnFailureListener {
                Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                isLoading = false
            }
    }

    // UI part
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text("Loading...", fontSize = 18.sp)
        }
    } else if (data?.textBased !="") {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(text = data?.textBased ?: "App Locked", fontSize = 20.sp)
        }
    }
}
