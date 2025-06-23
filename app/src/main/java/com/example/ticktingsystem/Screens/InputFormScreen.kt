package com.example.ticktingsystem.Screens

import android.app.Application
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ticktingsystem.DataModel.TicketData
import com.example.ticktingsystem.PrinterSetup.TicketPdfGenerator.previewTicketAsPdf
import com.example.ticktingsystem.PrinterSetup.TicketPrinter
import com.example.ticktingsystem.ViewModel.TicketViewModel
import com.example.ticktingsystem.ViewModel.ViewModelFactory
import com.example.ticktingsystem.utills.Spacing.HrLine
import com.example.ticktingsystem.utills.Spacing.MainColor
import com.example.ticktingsystem.utills.Spacing.Spacers
import com.example.ticktingsystem.utills.Spacing.bold
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InputFormScreen(vehicle1: String) {
    var nationality by rememberSaveable { mutableStateOf("")  }
    var CNIC by rememberSaveable { mutableStateOf("")  }
    var license by rememberSaveable { mutableStateOf("")  }
    var name by rememberSaveable { mutableStateOf("")  }
    var address by rememberSaveable { mutableStateOf("")  }
    var contactNumber by rememberSaveable { mutableStateOf("")  }
    var location by rememberSaveable { mutableStateOf("")  }
    var modeOfPayment by rememberSaveable { mutableStateOf("Manual/Paid")  }
    var violation by rememberSaveable { mutableStateOf("")  }
    var fineAmount by rememberSaveable { mutableStateOf("")  }
    var vehicle by rememberSaveable { mutableStateOf(vehicle1)  }
    var officerName by rememberSaveable { mutableStateOf("")  }
    var isSubmitted by rememberSaveable { mutableStateOf(false)  }
    val context = LocalContext.current
    val viewModel: TicketViewModel = viewModel(factory = ViewModelFactory(context.applicationContext as Application))

    if(isSubmitted){
        val randomFourDigit = (1000..9999).random()
        val now = LocalDateTime.now()
        val formatterDate = DateTimeFormatter.ofPattern("dd MMM yyyy")
        val formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss")
        val formatterTicket = DateTimeFormatter.ofPattern("dd/M/yy/HH/mm/ss")
        val autoDate = now.format(formatterDate)
        val autoTime= now.format(formatterTime)
        val autoTicketNumber = "$randomFourDigit/${now.format(formatterTicket)}"
        val ticketData = TicketData(
            beat = "Beat-26, South 1, N5 South",
            ticketNumber = autoTicketNumber,
            date = autoDate,
            time = autoTime,
            location = location,
            paymentMode = modeOfPayment,
            vehicle = vehicle1,
            driverName = name,
            cnic = CNIC,
            contactNumber = contactNumber,
            licence = license,
            violation = violation,
            fineAmount = fineAmount,
            officerName = officerName,
            documentConfiscated = "None"
        )
        BasicAlertDialog(
            modifier = Modifier.fillMaxWidth(),
            onDismissRequest = {isSubmitted = false}
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth().background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.Center)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Issue Ticket with following details?",
                            fontSize = 17.sp,
                            fontWeight = bold
                        )
                        Spacers(5, "h")
                        HrLine()
                        Spacers(5, "h")
                        Text(
                            text = "National Highway & Motorway Police",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacers(5, "h")
                        HrLine()
                        TicketDetails(ticketData)

                        Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp), horizontalArrangement = Arrangement.End) {
                            TextButton(onClick = { isSubmitted = false }) { Text("Cancel", color = MainColor) }
                            Spacer(modifier = Modifier.width(8.dp))
                            TextButton(onClick = @androidx.annotation.RequiresPermission(android.Manifest.permission.BLUETOOTH_CONNECT) {
                                isSubmitted = false;
                                viewModel.insertTicket(ticketData);
                                previewTicketAsPdf(context, ticketData)
                                TicketPrinter.printTicket(context, ticketData) }) { Text("OK", color = MainColor) }
                        }
                    }
                }
            }
        }
    }
    Scaffold(topBar = {TopBarDesign1(vehicle1)}, content = { innerPadding->
        Box (modifier = Modifier.fillMaxSize().padding(innerPadding)){
            LazyColumn(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                item {
                    Column(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
                        OutlinedTextField(shape = RoundedCornerShape(12.dp),value =nationality , onValueChange = {nationality = it}, label = {Text(text = "Nationality*")}, modifier = Modifier.fillMaxWidth())
                        Spacers(3,"h")
                        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround){
                            Row(verticalAlignment = Alignment.CenterVertically) { Checkbox(checked = true, onCheckedChange = {}, colors = CheckboxDefaults.colors(checkedColor = MainColor));Text(text = "CNIC") }
                            Row(verticalAlignment = Alignment.CenterVertically) { Checkbox(checked = true, onCheckedChange = {}, colors = CheckboxDefaults.colors(checkedColor = MainColor));Text(text = "Licence") }
                        }
                        OutlinedTextField(shape = RoundedCornerShape(12.dp), value =CNIC, onValueChange = {CNIC = formatCNIC(it) }, isError = CNIC.isNotEmpty() && !isValidCNIC(CNIC), label = {Text(text = "CNIC*")}, modifier = Modifier.fillMaxWidth())
                        Spacers(12,"h")
                        Row (verticalAlignment = Alignment.CenterVertically){
                            OutlinedTextField(shape = RoundedCornerShape(12.dp),
                                value = license,
                                onValueChange = {license = it},
                                label = { Text(text = "Licence*") },
                                modifier = Modifier.fillMaxWidth(0.8f),
                            )
                            Spacers(4,"")
                            Spacer(modifier = Modifier.weight(1f))
                            Row(verticalAlignment = Alignment.CenterVertically) { Text(text = "N/A") ;Checkbox(checked = true, onCheckedChange = {}, colors = CheckboxDefaults.colors(checkedColor = MainColor))}
                        }
                        Spacers(14,"h")
                        OutlinedTextField(shape = RoundedCornerShape(12.dp), value =name , onValueChange = {name = it}, label = {Text(text = "Name*")}, modifier = Modifier.fillMaxWidth())
                        Spacers(12,"h")
                        OutlinedTextField(shape = RoundedCornerShape(12.dp), value =address , onValueChange = {address = it}, label = {Text(text = "Address*")}, modifier = Modifier.fillMaxWidth())
                        Spacers(12,"h")
                        OutlinedTextField(shape = RoundedCornerShape(12.dp), value =vehicle , onValueChange = {vehicle = it}, label = {Text(text = "Vehicle*")}, modifier = Modifier.fillMaxWidth())
                        Spacers(12,"h")
                        OutlinedTextField(shape = RoundedCornerShape(12.dp), value =contactNumber , onValueChange = {contactNumber = it}, label = {Text(text = "Contact Number*")}, modifier = Modifier.fillMaxWidth())
                        Spacers(12,"h")
                        OutlinedTextField(shape = RoundedCornerShape(12.dp), value =location, onValueChange = {location = it}, label = {Text(text = "Location*")}, modifier = Modifier.fillMaxWidth())
                        Spacers(12,"h")
                        OutlinedTextField(shape = RoundedCornerShape(12.dp), value =modeOfPayment , onValueChange = {modeOfPayment = it}, label = {Text(text = "Mode Of Payment")}, modifier = Modifier.fillMaxWidth())
                        Spacers(12,"h")
                        OutlinedTextField(shape = RoundedCornerShape(12.dp), value =violation , onValueChange = {violation = it}, label = {Text(text = "Violation")}, modifier = Modifier.fillMaxWidth())
                        Spacers(12,"h")
                        OutlinedTextField(shape = RoundedCornerShape(12.dp), value = fineAmount, onValueChange = {fineAmount = it}, label = {Text(text = "Fine Amount")}, modifier = Modifier.fillMaxWidth())
                        Spacers(12,"h")
                        OutlinedTextField(shape = RoundedCornerShape(12.dp), value =officerName , onValueChange = {officerName = it}, label = {Text(text = "Patrolling Officer")}, modifier = Modifier.fillMaxWidth())
                        Spacers(12,"h")
                        Button(onClick = {
                            if (!isFieldNotEmpty(nationality, CNIC, license, name, address, contactNumber, location, violation, fineAmount, vehicle, officerName)) {
                                Toast.makeText(context, "Please fill all required fields.", Toast.LENGTH_SHORT).show()
                                return@Button
                            }

                            if (!isValidCNIC(CNIC)) {
                                Toast.makeText(context, "Invalid CNIC format. Correct format: 35201-1234567-1", Toast.LENGTH_SHORT).show()
                                return@Button
                            }

                            isSubmitted = true}, colors = ButtonDefaults.buttonColors(containerColor = MainColor)) {Text(text = "Submit") }

                    }
                }
            }
        }
    })
}

fun formatCNIC(input: String): String {
    val digits = input.filter { it.isDigit() }.take(13) // Max 13 digits

    return when (digits.length) {
        in 0..5 -> digits
        in 6..12 -> "${digits.substring(0, 5)}-${digits.substring(5)}"
        13 -> "${digits.substring(0, 5)}-${digits.substring(5, 12)}-${digits.substring(12)}"
        else -> "${digits.substring(0, 5)}-${digits.substring(5, 12)}-${digits.substring(12)}"
    }
}

fun isValidCNIC(cnic: String): Boolean {
    val regex = Regex("^\\d{5}-\\d{7}-\\d{1}$")
    return cnic.matches(regex)
}

fun isFieldNotEmpty(vararg fields: String): Boolean {
    return fields.all { it.trim().isNotEmpty() }
}

@Composable
fun TopBarDesign1(vehicle: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(top = 26.dp, bottom = 12.dp).shadow(elevation = 1.dp).padding(12.dp), verticalAlignment = Alignment.CenterVertically){
        Icon(imageVector = Icons.Default.Menu, contentDescription = "", tint = Color.Gray)
        Spacers(12,"")
        Text(text = "Issue Ticket for $vehicle", fontSize = 18.sp, color = Color.Gray)
    }

}