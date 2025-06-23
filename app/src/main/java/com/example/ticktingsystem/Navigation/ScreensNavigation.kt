package com.example.ticktingsystem.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ticktingsystem.Screens.ChooseVehiclesScreen
import com.example.ticktingsystem.Screens.HomeScreen
import com.example.ticktingsystem.Screens.InputFormScreen
import com.example.ticktingsystem.Screens.LoginScreen
import com.example.ticktingsystem.Screens.OutputScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreensNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login_screen") {
        composable("login_screen") {
            LoginScreen(navController)
        }
        composable("home_screen") {
            HomeScreen(navController)
        }
        composable("choose_vehicles_screen") {
            ChooseVehiclesScreen(navController)
        }
        composable("input_screen/{name}") {backStackEntry->
            val nameData = backStackEntry.arguments?.getString("name")
            val decodedName = URLDecoder.decode(nameData, StandardCharsets.UTF_8.toString())
            InputFormScreen(decodedName ?: "")
        }
        composable("output_screen") {
            OutputScreen()
        }
    }
}