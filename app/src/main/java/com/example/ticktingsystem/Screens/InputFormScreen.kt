package com.example.ticktingsystem.Screens

import android.Manifest
import android.app.Application
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ticktingsystem.DataModel.Offense
import com.example.ticktingsystem.DataModel.TicketData
import com.example.ticktingsystem.PrinterSetup.TicketPdfGenerator.previewTicketAsPdf
import com.example.ticktingsystem.ViewModel.TicketViewModel
import com.example.ticktingsystem.ViewModel.ViewModelFactory
import com.example.ticktingsystem.utills.Spacing.HrLine
import com.example.ticktingsystem.utills.Spacing.MainColor
import com.example.ticktingsystem.utills.Spacing.Spacers
import com.example.ticktingsystem.utills.Spacing.bold
import com.example.ticktingsystem.utills.Spacing.btnColor
import com.example.ticktingsystem.utills.Spacing.btnColor2
import com.example.ticktingsystem.utills.Spacing.offenseList
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InputFormScreen(vehicle1: String) {
    var nationality by rememberSaveable { mutableStateOf("Pakistani")  }
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
    var officerName by rememberSaveable { mutableStateOf("") }
    var isSubmitted by rememberSaveable { mutableStateOf(false) }
    var selectedViolations by rememberSaveable { mutableStateOf<List<String>>(emptyList()) }
    var fineTotal by rememberSaveable { mutableStateOf(0) }
    var vehicleRegistration by rememberSaveable { mutableStateOf("") }
    var showOffenseDialog by remember { mutableStateOf(false) }
    var showCustomPenaltyDialog by remember { mutableStateOf<Offense?>(null) }
    var licenceSelectedOptions by rememberSaveable { mutableStateOf("LTV") }
    val context = LocalContext.current
    var expanded by rememberSaveable { mutableStateOf(false) }
    val viewModel: TicketViewModel = viewModel(factory = ViewModelFactory(context.applicationContext as Application))
    var northOrSouth by rememberSaveable { mutableStateOf("") }
    var isNorth by rememberSaveable { mutableStateOf(false) }
    var isSouth by rememberSaveable { mutableStateOf(false) }
    var oneRepeat by rememberSaveable { mutableStateOf(0) }
    var count by rememberSaveable { mutableStateOf(0) }
    if (showOffenseDialog) {
        AlertDialog(
            onDismissRequest = { showOffenseDialog = false },
            title = { Text("Select Offense") },
            text = {
                LazyColumn {
                    itemsIndexed(offenseList) {i, offense ->
                        Text(
                            "${i+1}. ${offense.title} - ${offense.description} (${offense.minPenalty}${if (offense.minPenalty != offense.maxPenalty) "–${offense.maxPenalty}" else ""})",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    if (offense.minPenalty != offense.maxPenalty) {
                                        showCustomPenaltyDialog = offense
                                    } else {
                                        count+=1
                                        selectedViolations = selectedViolations + "${count}. ${offense.title} - ${offense.description}. - ${offense.minPenalty}"
                                        fineTotal += offense.minPenalty
                                    }
                                    showOffenseDialog = false
                                }
                                .padding(8.dp)
                        )
                    }
                }
            },
            confirmButton = {},
            dismissButton = {}
        )
    }
    if (showCustomPenaltyDialog != null) {
        var customAmount by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { showCustomPenaltyDialog = null },
            title = { Text("Enter Custom Penalty") },
            text = {
                Column {
                    Text("Offense: ${showCustomPenaltyDialog!!.title} - ${showCustomPenaltyDialog!!.description}")
                    OutlinedTextField(
                        value = customAmount,
                        onValueChange = { customAmount = it },
                        label = { Text("Penalty Amount (${showCustomPenaltyDialog!!.minPenalty}-${showCustomPenaltyDialog!!.maxPenalty})") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    val amount = customAmount.toIntOrNull()
                    val offense = showCustomPenaltyDialog!!
                    if (amount != null && amount in offense.minPenalty..offense.maxPenalty) {
                        count+=1
                        selectedViolations = selectedViolations + "$count. ${offense.title} - ${offense.description}. - $amount"
                        fineTotal += amount
                        showCustomPenaltyDialog = null
                    }
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showCustomPenaltyDialog = null }) {
                    Text("Cancel")
                }
            }
        )
    }




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
            vehicle = "$vehicle1 ${if(vehicleRegistration.isNotEmpty()){vehicleRegistration}else{""}}",
            driverName = name,
            cnic = CNIC,
            contactNumber = contactNumber,
            licence = "$licenceSelectedOptions $license",
            violation = selectedViolations.joinToString("\n"),
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
                            TextButton(onClick = @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT) {
                                isSubmitted = false;
                                viewModel.insertTicket(ticketData);
                                previewTicketAsPdf(context, ticketData)
//                                TicketPrinter.printTicket(context, ticketData)
                            }) { Text("OK", color = MainColor) }
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
                        Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                            OutlinedTextField(shape = RoundedCornerShape(12.dp), value =CNIC, onValueChange = {CNIC = formatCNIC(it) }, keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number), isError = CNIC.isNotEmpty() && !isValidCNIC(CNIC), label = {Text(text = "CNIC*")}, modifier = Modifier.weight(1.8f).padding(2.dp))
                            Box(modifier = Modifier.size(height = 58.dp, width = 45.dp).weight(0.7f).padding(1.dp).background(brush = Brush.linearGradient(btnColor), shape = RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center){ Text(text = "Scan", color = Color.White)}
                            Box(modifier = Modifier.size(height = 58.dp, width = 45.dp).weight(0.7f).padding(1.dp).background(brush = Brush.linearGradient(btnColor), shape = RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center){ Text(text = "Verify",color = Color.White)}
                        }
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
                        OutlinedTextField(shape = RoundedCornerShape(12.dp), value =contactNumber , onValueChange = {if (it.length <= 11 && it.all { char -> char.isDigit() }) { contactNumber = it }}, keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number), label = {Text(text = "Contact Number*")}, modifier = Modifier.fillMaxWidth())
                        Spacers(12,"h")
                        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                            Row(verticalAlignment = Alignment.CenterVertically) { RadioButton(selected = true, onClick = {}); Text(text = "Male") }
                            Row (verticalAlignment = Alignment.CenterVertically){ RadioButton(selected = false, onClick = {}); Text(text = "Female") }
                            Row(verticalAlignment = Alignment.CenterVertically) { RadioButton(selected = false, onClick = {}); Text(text = "Other") }
                        }
                        Spacers(12,"h")
                        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                            Row(verticalAlignment = Alignment.CenterVertically) { RadioButton(selected = true, onClick = {}); Text(text = "Driver") }
                            Row (verticalAlignment = Alignment.CenterVertically){ RadioButton(selected = false, onClick = {}); Text(text = "Owner") }
                            Row(verticalAlignment = Alignment.CenterVertically) { RadioButton(selected = false, onClick = {}); Text(text = "Other") }
                        }
                        Spacers(12,"h")
                        DropdownOutlinedField(
                            label = "Licence Type", options = listOf("LTV", "HTV", "PSV"),
                            selectedOption =licenceSelectedOptions ,
                            onOptionSelected = {licenceSelectedOptions = it},
                        )
                        Spacers(12,"h")
                        Text(text = "Vehicle")
                        HrLine()
                        Spacers(12,"h")
                        Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                            OutlinedTextField(shape = RoundedCornerShape(12.dp), value =vehicleRegistration, onValueChange = {vehicleRegistration = it }, label = {Text(text = "Vehicle Registration Number*", fontSize = 14.sp)}, modifier = Modifier.weight(2f).padding(2.dp))
                            Box(modifier = Modifier.size(height = 58.dp, width = 40.dp).weight(0.7f).padding(1.dp).background(brush = Brush.linearGradient(btnColor), shape = RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center){ Text(text = "Verify", color = Color.White)}
                        }
                        Spacers(12,"h")
                        OutlinedTextField(shape = RoundedCornerShape(12.dp), value ="", onValueChange = {}, label = {Text(text = "Company")}, modifier = Modifier.fillMaxWidth())
                        Spacers(12,"h")
                        Text(text = "Violation(s)")
                        HrLine()
                        Spacers(12,"h")
                        OutlinedTextField(shape = RoundedCornerShape(12.dp), value = selectedViolations.joinToString("  \n") , onValueChange = {}, label = {Text(text = "Violation")}, modifier = Modifier.fillMaxWidth(), readOnly = true, trailingIcon = {Icon(Icons.Default.ArrowDropDown, contentDescription = "", modifier = Modifier.clickable{showOffenseDialog = true})})
                        Spacers(12,"h")
                        Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                            OutlinedTextField(shape = RoundedCornerShape(12.dp), value =location, onValueChange = {location = it }, label = {Text(text = "Place of Ticket*")}, modifier = Modifier.weight(1.5f).padding(2.dp))
                            Box(modifier = Modifier.size(height = 58.dp, width = 50.dp).weight(1f).padding(1.dp).background(brush = Brush.linearGradient(btnColor), shape = RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center){ Text(text = "Fetch Location", color = Color.White)}
                        }
                        Spacers(12,"h")
                        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                            Row(verticalAlignment = Alignment.CenterVertically) { RadioButton(selected = isNorth, onClick = {isNorth = !isNorth;oneRepeat = 1}); Text(text = "North") }
                            Row (verticalAlignment = Alignment.CenterVertically){ RadioButton(selected = isSouth, onClick = {isSouth = !isSouth;oneRepeat = 1}); Text(text = "South") }
                        }
                        if(location.isNotEmpty()){
                            if(oneRepeat == 1){
                                if(isNorth){
                                    oneRepeat = 2
                                    location += " N"
                                }else if(isSouth){
                                    oneRepeat = 2
                                    location += " S"
                                }
                            }
                        }
                        Spacers(12,"h")
                        OutlinedTextField(shape = RoundedCornerShape(12.dp), value = fineTotal.toString(), onValueChange = {fineAmount = fineTotal.toString()}, label = {Text(text = "Fine Amount")}, modifier = Modifier.fillMaxWidth())

                        Spacers(12,"h")
                        OutlinedTextField(shape = RoundedCornerShape(12.dp), value =officerName , onValueChange = {officerName = it}, label = {Text(text = "Patrolling Officer")}, modifier = Modifier.fillMaxWidth())
                        Spacers(12,"h")
//                        Button(onClick = {
//                            fineAmount = fineTotal.toString()
//                            if (!isFieldNotEmpty(nationality, CNIC, license, name, address, contactNumber, location, violation, fineAmount, vehicle, officerName)) {
//                                Toast.makeText(context, "Please fill all required fields.", Toast.LENGTH_SHORT).show()
//                                return@Button
//                            }
//
//                            if (!isValidCNIC(CNIC)) {
//                                Toast.makeText(context, "Invalid CNIC format. Correct format: 35201-1234567-1", Toast.LENGTH_SHORT).show()
//                                return@Button
//                            }
//
//                            isSubmitted = true}, colors = ButtonDefaults.buttonColors(containerColor = MainColor)) {Text(text = "Submit") }
                        Box(modifier = Modifier.fillMaxWidth().height(80.dp).padding(12.dp).background(brush = Brush.linearGradient(btnColor2), shape = RoundedCornerShape(8.dp)).clip(shape = RoundedCornerShape(8.dp)).clickable{
                            fineAmount = fineTotal.toString()
                            if (!isFieldNotEmpty(nationality, CNIC, license, name, address, contactNumber, location,  fineAmount, vehicle, officerName)) {
                                Toast.makeText(context, "Please fill all required fields.", Toast.LENGTH_SHORT).show()
                                return@clickable
                            }

                            if (!isValidCNIC(CNIC)) {
                                Toast.makeText(context, "Invalid CNIC format. Correct format: 35201-1234567-1", Toast.LENGTH_SHORT).show()
                                return@clickable
                            }
                            isSubmitted = true
                        }, contentAlignment = Alignment.Center){ Text(text = "Issue Ticket", color = Color.White)}


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
    Row(modifier = Modifier.fillMaxWidth().padding(top = 26.dp, bottom = 12.dp)
//        .shadow(elevation = 1.dp)
        .padding(12.dp), verticalAlignment = Alignment.CenterVertically){
        Icon(imageVector = Icons.Default.Menu, contentDescription = "", tint = Color.Gray)
        Spacers(12,"")
        Text(text = "Issue Ticket for $vehicle", fontSize = 18.sp, color = Color.Gray)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownOutlinedField(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
//                .menuAnchor(type, enabled)
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onOptionSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}
