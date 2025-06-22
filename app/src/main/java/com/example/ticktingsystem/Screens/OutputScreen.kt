package com.example.ticktingsystem.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticktingsystem.utills.Spacing.HrLine
import com.example.ticktingsystem.utills.Spacing.MainColor
import com.example.ticktingsystem.utills.Spacing.Spacers
import com.example.ticktingsystem.utills.Spacing.bold


@Preview
@Composable
fun OutputScreen() {
    Scaffold {innerPadding->
        Box (modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)){
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)) {
                AlertDialog(onDismissRequest = {},
                    confirmButton = { TextButton(onClick = {}){Text(text = "OK", color = MainColor)} },
                    dismissButton = { TextButton(onClick = {}){Text(text = "Cancel", color = MainColor)} },
                    text = {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Spacers(5,"h")
                        Text(text = "Issue Ticket with following details?", fontSize = 17.sp, fontWeight = bold)
                        Spacers(5,"h")
                        HrLine()
                        Spacers(5,"h")
                        Text(text = "National Highway & Motorway Police", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                        Spacers(5,"h")
                        HrLine()
                        TicketDetails()
                    }
                }, modifier = Modifier.fillMaxWidth())
            }
        }

    }

}

@Composable
fun TicketDetails() {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 10.dp)) {
        RowDetails()
        RowDetails()
        RowDetails()
        RowDetails()
        RowDetails()
        RowDetails()
        RowDetails()
        RowDetails()
        RowDetails()
        RowDetails()
        RowDetails()
        RowDetails()
    }
}

@Composable
fun RowDetails() {
    Spacers(6,"h")
    Row (modifier = Modifier.padding(top = 2.dp, start = 4.dp, end = 4.dp), verticalAlignment = Alignment.CenterVertically){
        Text(text = "Beat Name:", fontSize = 16.sp, fontWeight = bold, color = Color.Black)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "Beat-26, South1, N5 South", fontSize = 14.sp)
    }
    Spacers(6,"h")
    HrLine()
}