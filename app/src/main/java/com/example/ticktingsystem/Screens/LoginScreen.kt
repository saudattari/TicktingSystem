package com.example.ticktingsystem.Screens

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ticktingsystem.R
import com.example.ticktingsystem.utills.Spacing.MainColor
import com.example.ticktingsystem.utills.Spacing.Spacers
import com.example.ticktingsystem.utills.Spacing.bold


@Composable
fun LoginScreen(navController: NavController) {
    var userID by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.logo2),
                    contentDescription = "",
                    modifier = Modifier.size(200.dp)
                )
                Spacers(20, "h")
                Text(text = "Login", color = MainColor, fontSize = 25.sp, fontWeight = bold)
                Spacers(12, "h")
                Text(
                    text = "Login with your registered User ID and Password",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Spacers(30, "h")
                OutlinedTextField(
                    value = userID,
                    onValueChange = { userID = it },
                    placeholder = { Text(text = "User ID*") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacers(12, "h")
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text(text = "Password*") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                )
                Spacers(20, "h")
                Button(
                    onClick = {
                        if (userID == "saudDeveloper" && password == "12345678") {
                            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                            navController.navigate("home_screen"){
                                popUpTo(0){
                                    inclusive = true
                                }
                            }
                        } else {
                            Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MainColor),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Sign In")
                }
            }
        }
    }
}
