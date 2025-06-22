package com.example.ticktingsystem.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ticktingsystem.utills.Spacing.MainColor
import com.example.ticktingsystem.utills.Spacing.Spacers


@Preview
@Composable
fun InputFormScreen() {
    Scaffold{innerPadding->
        Box (modifier = Modifier.fillMaxSize().padding(innerPadding)){
            Column(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
                OutlinedTextField(shape = RoundedCornerShape(12.dp),value ="" , onValueChange = {}, placeholder = {Text(text = "Nationality*")}, modifier = Modifier.fillMaxWidth())
                Spacers(3,"h")
                Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround){
                    Row(verticalAlignment = Alignment.CenterVertically) { Checkbox(checked = true, onCheckedChange = {}, colors = CheckboxDefaults.colors(checkedColor = MainColor));Text(text = "CNIC") }
                    Row(verticalAlignment = Alignment.CenterVertically) { Checkbox(checked = true, onCheckedChange = {}, colors = CheckboxDefaults.colors(checkedColor = MainColor));Text(text = "Licence") }
                }
                OutlinedTextField(shape = RoundedCornerShape(12.dp), value ="" , onValueChange = {}, placeholder = {Text(text = "CNIC*")}, modifier = Modifier.fillMaxWidth())
                Spacers(12,"h")
                Row (verticalAlignment = Alignment.CenterVertically){
                    OutlinedTextField(shape = RoundedCornerShape(12.dp),
                        value = "",
                        onValueChange = {},
                        placeholder = { Text(text = "Licence*") },
                        modifier = Modifier.fillMaxWidth(0.8f),
                    )
                    Spacers(4,"")
                    Spacer(modifier = Modifier.weight(1f))
                    Row(verticalAlignment = Alignment.CenterVertically) { Text(text = "N/A") ;Checkbox(checked = true, onCheckedChange = {}, colors = CheckboxDefaults.colors(checkedColor = MainColor))}
                }
                Spacers(14,"h")
                OutlinedTextField(shape = RoundedCornerShape(12.dp), value ="" , onValueChange = {}, placeholder = {Text(text = "Name")}, modifier = Modifier.fillMaxWidth())
                Spacers(12,"h")
                OutlinedTextField(shape = RoundedCornerShape(12.dp), value ="" , onValueChange = {}, placeholder = {Text(text = "Address")}, modifier = Modifier.fillMaxWidth())
                Spacers(12,"h")
                OutlinedTextField(shape = RoundedCornerShape(12.dp), value ="" , onValueChange = {}, placeholder = {Text(text = "Contact Number")}, modifier = Modifier.fillMaxWidth())
                Spacers(12,"h")
                OutlinedTextField(shape = RoundedCornerShape(12.dp), value ="" , onValueChange = {}, placeholder = {Text(text = "Location")}, modifier = Modifier.fillMaxWidth())
                Spacers(12,"h")
                OutlinedTextField(shape = RoundedCornerShape(12.dp), value ="" , onValueChange = {}, placeholder = {Text(text = "Mode Of Payment")}, modifier = Modifier.fillMaxWidth())
                Spacers(12,"h")
                OutlinedTextField(shape = RoundedCornerShape(12.dp), value ="" , onValueChange = {}, placeholder = {Text(text = "Violation")}, modifier = Modifier.fillMaxWidth())
                Spacers(12,"h")

            }
        }

    }
}