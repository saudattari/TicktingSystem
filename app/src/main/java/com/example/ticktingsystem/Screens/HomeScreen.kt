package com.example.ticktingsystem.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ticktingsystem.R
import com.example.ticktingsystem.ViewModel.TicketViewModel
import com.example.ticktingsystem.utills.Spacing
import com.example.ticktingsystem.utills.Spacing.Spacers
import com.example.ticktingsystem.utills.Spacing.bold
import com.example.ticktingsystem.utills.Spacing.lists
import com.example.ticktingsystem.utills.Spacing.lists2
import com.example.ticktingsystem.utills.Spacing.regular
import com.example.ticktingsystem.utills.Spacing.twoCard


@Composable
fun HomeScreen(navController: NavController) {
    val ticketViewModel: TicketViewModel = viewModel()
    val total  = ticketViewModel.total.collectAsState()

//    val lists2 = listOf("Tickets", "Helps Given","Helps Given","Helps Given")
    Scaffold(topBar = {TopBarDesign2()}) { innerPadding->
        Box(modifier = Modifier.padding(innerPadding)){
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                ProfilePart()
                Spacers(20,"h")
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
fun DataFun(value: Int, data: twoCard) {
    Card(modifier = Modifier.padding(4.dp), elevation = CardDefaults.cardElevation(defaultElevation = 3.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Row (modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically){
            Box(modifier = Modifier
                .size(30.dp)
                .background(color = data.color, shape = CircleShape), contentAlignment = Alignment.Center){
                Icon(painter = painterResource(data.icon), contentDescription = "ticket", modifier = Modifier.size(18.dp), tint = Color.White)
            }
            Spacers(8,"")
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = if(data.text == "Tickets Issued"){value.toString()}else{data.value.toString()}, color = data.color, fontWeight = bold, fontSize = 18.sp)
                Text(text = data.text, color = Color.Gray, fontSize = if(data.text == "Tickets Issued"){14.sp}else{16.sp})
            }
        }
    }
}

@Composable
fun DataFun2(value: Int, data: Spacing.twoCard1, onClick: (String) -> Unit) {
    Card(modifier = Modifier
        .padding(4.dp)
        .clip(shape = RoundedCornerShape(12.dp))
        .clickable { onClick(data.text) }, elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Column(modifier = Modifier.fillMaxWidth().padding(14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painterResource(data.icon), contentDescription = "", modifier = Modifier.size(80.dp))
            Text(text = data.text, color = Color.Gray, fontSize = 15.sp, textAlign = TextAlign.Center)
        }
    }
}

@Preview
@Composable
fun ProfilePart() {
    Row(modifier = Modifier.fillMaxWidth().padding(4.dp),verticalAlignment = Alignment.CenterVertically){
        Box(modifier = Modifier.size(80.dp).background(color = Color(0xFF777E98), shape = CircleShape).border(width = 3.dp, color = Color.White, shape = CircleShape).shadow(elevation = 2.dp, shape = CircleShape), contentAlignment = Alignment.Center){
            Image(painterResource(R.drawable.man), contentDescription = "",modifier = Modifier.size(75.dp).padding(top = 4.dp).clip(shape = CircleShape), colorFilter = ColorFilter.tint(Color(
                0xFFE7E6E6
            )
            ))
        }
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(text = "Nadeem Ahmed sip", fontSize = 18.sp, color = Color.Gray, fontFamily = regular, fontWeight = FontWeight.Black)
            Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                Text(text = "Beat-26 -South 1 - N5 South", fontSize = 15.sp, color = Color(0xFF2F79FC), fontFamily = regular)
                Spacer(Modifier.weight(1f))
                Box(modifier = Modifier.size(25.dp).background(color = Color(0xFFE8E8E8), shape = RoundedCornerShape(90.dp)), contentAlignment = Alignment.Center){
                    Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "", tint = Color(
                        0xFF2F79FC
                    )
                    )
                }
            }
            Text(text = "Carriageway - Tiger-1", fontSize = 15.sp, color = Color.Gray, fontFamily = regular)
        }
    }

}

//@Preview
@Composable
fun TopBarDesign2() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 26.dp, bottom = 12.dp)
//        .shadow(elevation = 1.dp)
        .padding(12.dp), verticalAlignment = Alignment.CenterVertically){
        Icon(imageVector = Icons.Default.Menu, contentDescription = "", tint = Color.Gray)
        Spacers(12,"")
        Text(text = "e-NHP", fontSize = 18.sp, color = Color.Gray, fontFamily = regular)
    }

}