package com.example.ticktingsystem.utills

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

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



    //fonts
    val bold = FontWeight.Bold
    val light = FontWeight.Light

}