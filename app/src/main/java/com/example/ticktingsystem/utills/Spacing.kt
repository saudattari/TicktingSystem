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
import com.example.ticktingsystem.DataModel.Offense
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

    val btnColor = listOf(Color(0xFF1E88E5), Color(0xFF161C88))

    val MainColor = Color(0xFF161C88)
@Composable
    fun HrLine(){
        HorizontalDivider(color = Color(0xFF1E88E5), thickness = 1.dp)
    }

    //fonts
    val bold = FontWeight.Bold
    val light = FontWeight.Light

    val offenseList = listOf(
        Offense("A20", "Driving when disqualified", 1000, 1000, "1."),
        Offense("B22-2", "Disobey red flashing", 2000, 2000, "2."),
        Offense("C15", "Over Speeding", 100, 2200, "3."),
        Offense("B43", "No helmet", 1000, 1000, "4.")
    )


    //list Data
    val dataList = listOf(
        VehicleData("Car", R.drawable.vehicle_13),
        VehicleData("Coach/Bus", R.drawable.bus),
        VehicleData("Flying Coach", R.drawable.flying_coach),
        VehicleData("Jeep", R.drawable.vehicle_14),
        VehicleData("Truck", R.drawable.vehicle_5),
        VehicleData("Pickup", R.drawable.vehicle_12),
        VehicleData("Bike", R.drawable.vehicle_17),
        VehicleData("Riksha", R.drawable.riksha),
        VehicleData("Van/APV", R.drawable.van),
        VehicleData("Trailer", R.drawable.vehicle_2),
        VehicleData("Oil Carrier", R.drawable.oil_tanker),
        VehicleData("Car Carrier", R.drawable.vehicle_3),
        VehicleData("Dumper", R.drawable.dumper),
        VehicleData("Gas Bowser", R.drawable.vehicle_23),
        VehicleData("Crane", R.drawable.vehicle_22),
        VehicleData("Harvester", R.drawable.harvester),
        VehicleData("Milk Tanker", R.drawable.vehicle_20),
        VehicleData("Tractor", R.drawable.tracktor),
        VehicleData("Bike Carrier", R.drawable.vehicle_4),
        VehicleData("Pedestrian", R.drawable.walk),
        VehicleData("Water Bowser", R.drawable.water_tank),
        VehicleData("Animal Drawn Vehicle", R.drawable.vehicle_27)
    )

}