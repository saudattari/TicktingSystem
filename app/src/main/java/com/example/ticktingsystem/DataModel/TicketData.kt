package com.example.ticktingsystem.DataModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tickets")
data class TicketData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val beat: String,
    val ticketNumber: String,
    val date: String,
    val time: String,
    val location: String,
    val paymentMode: String,
    val vehicle: String,
    val driverName: String,
    val cnic: String,
    val contactNumber: String,
    val licence: String,
    val violation: String,
    val documentConfiscated: String,
    val fineAmount: String,
    val officerName: String
)