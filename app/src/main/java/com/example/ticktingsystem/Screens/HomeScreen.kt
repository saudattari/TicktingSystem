package com.example.ticktingsystem.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ticktingsystem.R
import com.example.ticktingsystem.ViewModel.TicketViewModel
import com.example.ticktingsystem.utills.Spacing.Spacers
import com.example.ticktingsystem.utills.Spacing.bold


@Composable
fun HomeScreen(navController: NavController) {
    val ticketViewModel: TicketViewModel = viewModel()
    val total  = ticketViewModel.total.collectAsState()
    val lists = listOf("Tickets Issued", "Helps Given")
    val lists2 = listOf("Tickets", "Helps Given","Helps Given","Helps Given")
    Scaffold { innerPadding->
        Box(modifier = Modifier.padding(innerPadding)){
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 40.dp), horizontalAlignment = Alignment.CenterHorizontally) {

                LazyVerticalGrid(columns = GridCells.Adaptive(110.dp)) {
                    items(lists) {
                        DataFun(total.value, it)
                    }
                }
                Spacers(20,"h")
                LazyVerticalGrid(columns = GridCells.Adaptive(110.dp)) {
                    items(lists2) {
                        DataFun2(total.value, it){
                            if(it == "Tickets"){
                            navController.navigate("choose_vehicles_screen")
                            }
                        }
                    }
                }
            }
        }

    }

}

@Composable
fun DataFun(value: Int, data: String) {
    Card(modifier = Modifier.padding(4.dp), elevation = CardDefaults.cardElevation(defaultElevation = 3.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Row (modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically){
            Box(modifier = Modifier.size(30.dp).background(Color(0xFF1FDC01), shape = CircleShape), contentAlignment = Alignment.Center){
                Icon(painter = painterResource(R.drawable.ticket), contentDescription = "ticket", modifier = Modifier.size(20.dp), tint = Color.White)
            }
            Spacers(8,"")
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = value.toString(), color = Color(0xFF1FDC01), fontWeight = bold, fontSize = 18.sp)
                Text(text = data, color = Color.Gray, fontSize = if(data == "Tickets Issued"){14.sp}else{16.sp})
            }
        }
    }
}

@Composable
fun DataFun2(value: Int, data: String,onClick:(String)-> Unit) {
    Card(modifier = Modifier.padding(4.dp)
        .clip(shape = RoundedCornerShape(12.dp))
        .clickable {onClick(data)}, elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Column(modifier = Modifier.padding(14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painterResource(R.drawable.traffic_car), contentDescription = "", modifier = Modifier.size(100.dp))
            Text(text = "Tickets", color = Color.Gray, fontSize = 18.sp)
        }
    }
}