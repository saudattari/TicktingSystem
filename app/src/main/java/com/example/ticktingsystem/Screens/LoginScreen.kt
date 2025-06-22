package com.example.ticktingsystem.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticktingsystem.R
import com.example.ticktingsystem.utills.Spacing.MainColor
import com.example.ticktingsystem.utills.Spacing.Spacers
import com.example.ticktingsystem.utills.Spacing.bold


@Preview
@Composable
fun LoginScreen() {
    var userID by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    Scaffold {innerPadding->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)){
            Column(modifier = Modifier.fillMaxWidth().padding(14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(R.drawable.logo2), contentDescription = "", modifier = Modifier.size(200.dp))
                Spacers(20,"h")
                Text(text = "Login", color = MainColor, fontSize = 25.sp, fontWeight = bold)
                Spacers(12,"h")
                Text(text = "Login with your registered User ID and Password", color = Color.Gray, fontSize = 14.sp)
                Spacers(30,"h")
                OutlinedTextField(value = userID, onValueChange = {userID = it}, placeholder = {Text(text = "User ID*")}, modifier = Modifier.fillMaxWidth())
                Spacers(12,"h")
                OutlinedTextField(value = password, onValueChange = {password = it}, placeholder = {Text(text = "Password*")}, modifier = Modifier.fillMaxWidth())
                Spacers(20, "h")
                Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = MainColor), modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp)) {Text(text = "Sign In") }
            }
        }

    }
}