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
import com.example.ticktingsystem.DataModel.TicketData
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
                    }
                }, modifier = Modifier.fillMaxWidth())
            }
        }

    }

}

@Composable
fun TicketDetails(data: TicketData) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 10.dp)) {
        RowDetails(data.beat,"Beat Name:")
        RowDetails(data.ticketNumber, "Ticket Number:")
        RowDetails(data.date, "Date:")
        RowDetails(data.time, "Time:")
        RowDetails(data.location, "Location:")
        RowDetails(data.paymentMode, "Mode of Payment:")
        RowDetails(data.vehicle, "Vehicle:")
        RowDetails(data.driverName, "Driver's Name:")
        RowDetails(data.driverName, "Name:")
        RowDetails(data.cnic, "CNIC:")
        RowDetails(data.contactNumber, "Contact Number:")
        RowDetails(data.licence, "Licence:")
        RowDetails(data.violation, "Violation:")
        RowDetails(data.documentConfiscated, "Document Confiscated:")
        RowDetails(data.fineAmount, "Fine Amount:")
        RowDetails(data.officerName, "Patrolling Officer:")
    }
}

@Composable
fun RowDetails(data: String, key: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 2.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = key,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.weight(2f)
            )
            Text(
                text = data,
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.weight(1f),
                maxLines = 1
            )
        }
        HrLine()
    }
}
