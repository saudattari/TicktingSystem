package com.example.ticktingsystem.utills

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ticktingsystem.DataModel.VehicleData
import com.example.ticktingsystem.R

object Spacing {
    @Composable
    fun Spacers(value: Int,wh: String) {
        if(wh == "h"){
            Spacer(modifier = Modifier.height(value.dp))
        }else{
            Spacer(modifier = Modifier.width(value.dp))
        }
    }

    val MainColor = Color(0xFF161C88)
@Composable
    fun HrLine(){
        HorizontalDivider(color = Color(0xFF1E88E5), thickness = 1.dp)
    }

    //fonts
    val bold = FontWeight.Bold
    val light = FontWeight.Light


    //list Data
    val dataList = listOf(
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

}