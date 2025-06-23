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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreensNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login_screen") {
        composable("login_screen") {
//            LoginScreen()
//            HomeScreen()
//            ChooseVehiclesScreen()
            InputFormScreen()
//            OutputScreen()
        }
        composable("home_screen") {
            HomeScreen()
        }
        composable("choose_vehicles_screen") {
            ChooseVehiclesScreen()
        }
        composable("input_screen") {
            InputFormScreen()
        }
        composable("output_screen") {
            OutputScreen()
        }


    }
}