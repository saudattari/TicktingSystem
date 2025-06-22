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
import androidx.compose.ui.text.style.TextAlign
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
        VehicleData("Car", R.drawable.car),
        VehicleData("Coach/Bus", R.drawable.bus),
        VehicleData("Flying Coach", R.drawable.flying_coach),
        VehicleData("Jeep", R.drawable.jeep),
        VehicleData("Truck", R.drawable.truck),
        VehicleData("Pickup", R.drawable.pickup),
        VehicleData("Bike", R.drawable.motorcycle),
        VehicleData("Riksha", R.drawable.riksha),
        VehicleData("Van/APV", R.drawable.van),
        VehicleData("Trailer", R.drawable.trailer),
        VehicleData("Oil Carrier", R.drawable.oil_tanker),
        VehicleData("Car Carrier", R.drawable.car_carrier),
        VehicleData("Dumper", R.drawable.dumper),
        VehicleData("Gas Bowser", R.drawable.gas),
        VehicleData("Crane", R.drawable.crane),
        VehicleData("Harvester", R.drawable.harvester),
        VehicleData("Milk Tanker", R.drawable.milk),
        VehicleData("Tractor", R.drawable.tracktor),
        VehicleData("Bike Carrier", R.drawable.bike_carrier),
        VehicleData("Pedestrian", R.drawable.walk),
        VehicleData("Water Bowser", R.drawable.water_tank),
        VehicleData("Animal Drawn Vehicle", R.drawable.animal)

    )
    Scaffold { innerPadding->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)){
            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 90.dp)) {
                items(list) {
                    GridItem(it.name,it.image)
                }
            }
        }
    }
}

@Composable
fun GridItem(name: String, image: Int) {
    Card(modifier = Modifier.size(height = 120.dp, width = 40.dp).padding(horizontal = 4.dp, vertical = 14.dp), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), colors = CardDefaults.cardColors(Color.White)) {
        Column(modifier = Modifier.fillMaxWidth().padding(6.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painterResource(image), contentDescription = "", colorFilter = ColorFilter.tint(MainColor), modifier = Modifier.size(40.dp))
            Spacers(8,"h")
            Text(text = name, color = Color.Gray, fontSize = 13.sp, textAlign = TextAlign.Center)
        }
    }

}