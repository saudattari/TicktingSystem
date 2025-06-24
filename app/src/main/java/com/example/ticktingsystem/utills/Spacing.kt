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
    val btnColor2 = listOf(Color(0xFFFCC0C0), Color(0xFFEA2C2C))

    val MainColor = Color(0xFF161C88)
@Composable
    fun HrLine(){
        HorizontalDivider(color = Color(0xFF1E88E5), thickness = 1.dp)
    }

    //fonts
    val bold = FontWeight.Bold
    val light = FontWeight.Light

    val offenseList = listOf(
        Offense("A20", "Driving when disqualified", 1000, 1000),
        Offense("A21", "Applying for a licence without disclosing particulars", 500, 1000),
        Offense("A22", "Offence relating to construction of vehicle", 1000, 1000),
        Offense("A23", "Offence relating to permits", 1000, 2000),
        Offense("A24", "Overloading of goods 15% above limit", 1000, 5000),
        Offense("A25", "Overloading passengers 30% above limit", 1000, 5000),
        Offense("A26", "Offence relating to accidents", 1000, 1000),
        Offense("A27", "Taking vehicle without authority", 1000, 2000),
        Offense("A28", "Unauthorized interference with vehicle", 500, 1000),
        Offense("A29", "Willful disobedience of lawful orders", 1000, 1000),
        Offense("A30", "Using altered or forged licence", 2000, 2000),
        Offense("A31", "Driving transport vehicle without proper licence", 500, 1000),
        Offense("A32", "Unauthorized race or trial", 1000, 2000),
        Offense("A33", "Dangerous overtaking", 500, 1000),
        Offense("A34", "Driving 40KM/H over speed limit", 750, 1500),
        Offense("A35", "No certificate of fitness", 500, 1000),
        Offense("A36", "Not stopping when signalled by police", 5000, 10000),
        Offense("A37", "Driving under influence / mentally unfit", 5000, 10000),
        Offense("A38", "Reckless driving", 500, 1000),
        Offense("B20", "Speeding less than 40KM/H above limit", 2500, 2500),
        Offense("B21", "Overloading passengers less than 30%", 2500, 2500),
        Offense("B22-1", "Disobey amber signal", 1000, 1000),
        Offense("B22-2", "Disobey red flashing", 2000, 2000),
        Offense("B22-3", "Red light violation", 5000, 5000),
        Offense("B23", "Overloading goods <15%", 1000, 1000),
        Offense("B24", "Prohibited overtaking", 1500, 1500),
        Offense("B25", "Failure to yield vehicle", 1000, 1000),
        Offense("B26", "Interference with emergency vehicle", 5000, 5000),
        Offense("B27", "Over-dimension goods", 1000, 1000),
        Offense("B28", "No lights at night", 5000, 5000),
        Offense("B29", "Wrong side driving", 2500, 2500),
        Offense("B30", "Ignoring stop sign", 3000, 3000),
        Offense("B31", "Improper rail crossing", 1000, 1000),
        Offense("B32", "Tailgating / sharp cutting", 1000, 1000),
        Offense("B33", "Rear screen covered", 750, 750),
        Offense("B34", "Jumping traffic queue", 1000, 1000),
        Offense("B35", "Not dimming headlights", 1000, 1000),
        Offense("B36", "Driving wrong way in one way street", 1000, 1000),
        Offense("B37", "Using turn indicator for any purpose other than those prescribed", 750, 750),
        Offense("B38", "Plying where prohibited", 2000, 2000),
        Offense("B39", "Improper loading of goods", 3000, 3000),
        Offense("B40", "Failing to observe lighting hours", 1000, 1000),
        Offense("B41", "Obstructing traffic", 2000, 2000),
        Offense("B42", "Failure to observe slow sign", 2000, 2000),
        Offense("B43", "Driver of motor cycle without safety helmet", 1000, 1000),
        Offense("B44", "Failure to stop for a school bus", 1000, 1000),
        Offense("B45", "Prohibited lane changing", 1000, 1000),
        Offense("B46", "Disobeying yield sign", 1000, 1000),
        Offense("B47", "Failure to protect beginner driver", 1000, 1000),
        Offense("B48", "Failure to yield the right of way to pedestrian", 2000, 2000),
        Offense("B49", "Careless driving", 1500, 1500),
        Offense("B50", "Driving without driving licence", 5000, 5000),
        Offense("B51", "Driving an unregistered vehicle", 2000, 2000),
        Offense("B52", "Driving a vehicle without No fault insurance", 1000, 1000),
        Offense("B53", "Carrying passengers in dangerous position", 5000, 5000),
        Offense("B54", "Opening doors dangerously", 1000, 1000),
        Offense("B55", "Improper turn", 2500, 2500),
        Offense("B56", "Improper lane usage", 1000, 1000),
        Offense("B57", "Driving vehicle emitting smoke, vapours, etc.", 1250, 1250),
        Offense("B58", "Using horn in silence zone", 1000, 1000),
        Offense("B59", "Vehicle producing loud noise", 1000, 1000),
        Offense("B60", "Improper U-turn", 1000, 1000),
        Offense("B61", "Other violations in schedule", 750, 750),
        Offense("B61-a", "Use phone/tab while driving", 500, 500),
        Offense("B61-b", "Driving without seatbelt or passenger unbelted", 1500, 1500),
        Offense("B61-c", "Carrying child < 5 in front seat", 3000, 3000),
        Offense("B61-d", "Driving without side mirrors", 500, 500),
        Offense("B61-e", "Bike with more than one person", 500, 500),
        Offense("B61-f", "Dangerous passenger on bike", 500, 500),
        Offense("B61-h", "Overloading of goods beyond prescribed", 3000, 3000),
        Offense("B62", "More than 0.5 meter from the curb", 750, 750),
        Offense("B63", "Parking on a side walk", 750, 750),
        Offense("B64", "Less than 0.5 meter from another car", 750, 750),
        Offense("B65", "Parking at Zebra crossing", 750, 750),
        Offense("B66", "Less than 3 meters from fire hydrant", 750, 750),
        Offense("B67", "Less than 10 meters from a stop sign", 750, 750),
        Offense("B68", "Less than 10 meters from intersection", 750, 750),
        Offense("B69", "Parking in a No Parking Zone", 750, 750),
        Offense("B70", "Parking in front of an entrance to premises", 750, 750),
        Offense("B71", "Parking on a bus stop", 750, 750),
        Offense("B72", "Parking on a bridge", 750, 750),
        Offense("B73", "Parking on the road pavement outside", 750, 750),
        Offense("B74", "Parking on a footpath", 1000, 1000),
        Offense("B75", "Parking on or near the brow of a hill", 750, 750),
        Offense("B76", "Parking on grass verge", 750, 750),
        Offense("C21", "Any other offence", 500, 500),
        Offense("C22", "Repetition of C21", 1000, 1000)
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
        VehicleData("Riksha", R.drawable.vehicle_19),
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