package com.example.ticktingsystem.Screens

import android.widget.GridLayout
import android.widget.GridView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticktingsystem.DataModel.VehicleData
import com.example.ticktingsystem.R
import com.example.ticktingsystem.utills.Spacing.MainColor
import com.example.ticktingsystem.utills.Spacing.Spacers


@Preview
@Composable
fun ChooseVehiclesScreen() {
    val list = listOf(
        VehicleData("MotorCycle", R.drawable.traffic_car),
        VehicleData("MotorCycle", R.drawable.traffic_car),
        VehicleData("MotorCycle", R.drawable.traffic_car),
        VehicleData("MotorCycle", R.drawable.traffic_car),
        VehicleData("MotorCycle", R.drawable.traffic_car),
        VehicleData("MotorCycle", R.drawable.traffic_car),
        VehicleData("MotorCycle", R.drawable.traffic_car),
        VehicleData("MotorCycle", R.drawable.traffic_car),
        VehicleData("MotorCycle", R.drawable.traffic_car),
        VehicleData("MotorCycle", R.drawable.traffic_car),
        VehicleData("MotorCycle", R.drawable.traffic_car),
        VehicleData("MotorCycle", R.drawable.traffic_car),
        VehicleData("MotorCycle", R.drawable.traffic_car),
        VehicleData("MotorCycle", R.drawable.traffic_car),
        VehicleData("MotorCycle", R.drawable.traffic_car),
        VehicleData("MotorCycle", R.drawable.traffic_car),
        VehicleData("MotorCycle", R.drawable.traffic_car),
        VehicleData("MotorCycle", R.drawable.traffic_car),
        VehicleData("MotorCycle", R.drawable.traffic_car),
        VehicleData("MotorCycle", R.drawable.traffic_car),

    )
    Scaffold { innerPadding->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)){
            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
                items(list) {
                    GridItem(it.name,it.image)
                }
            }
        }
    }
}

@Composable
fun GridItem(name: String, image: Int) {
    Card(modifier = Modifier.size(height = 120.dp, width = 60.dp).padding(8.dp), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), colors = CardDefaults.cardColors(Color.White)) {
        Column(modifier = Modifier.fillMaxWidth().padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painterResource(image), contentDescription = "", colorFilter = ColorFilter.tint(MainColor), modifier = Modifier.size(40.dp))
            Spacers(12,"h")
            Text(text = name, color = Color.Gray, fontSize = 15.sp)
        }
    }

}