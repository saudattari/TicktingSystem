package com.example.ticktingsystem.PrinterSetup

import android.Manifest
import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.print.PageRange
import android.print.PrintAttributes
import android.print.PrintManager
import android.print.pdf.PrintedPdfDocument
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.compose.ui.graphics.Color
import com.dantsu.escposprinter.EscPosPrinter
import com.dantsu.escposprinter.connection.bluetooth.BluetoothConnection
import com.dantsu.escposprinter.connection.bluetooth.BluetoothPrintersConnections
import com.example.ticktingsystem.DataModel.TicketData
import java.io.FileOutputStream
import java.io.IOException
import java.nio.charset.Charset

object TicketPrinter {

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun printTicket(context: Context, ticketData: TicketData) {
        try {
            val connection = BluetoothPrinterConnection.getOutputStream()
            if (connection != null) {
                val content = buildReceiptText(ticketData)
                val bytes = content.toByteArray(Charset.forName("UTF-8"))
                connection.write(bytes)
                connection.flush()
            } else {
                Toast.makeText(context, "Printer not connected", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Print failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun buildReceiptText(ticket: TicketData): String {
        return """
        |           National Highway & Motorway Police
        |         ${ticket.beat}
        |
        |Ticket Number: ${ticket.ticketNumber}
        |Date: ${ticket.date}
        |Time: ${ticket.time}
        |Location: ${ticket.location}
        |Mode of Payment: ${ticket.paymentMode}
        |Vehicle: ${ticket.vehicle}
        |Driver's Name: ${ticket.driverName}
        |CNIC: ${ticket.cnic}
        |Contact Number: ${ticket.contactNumber}
        |Licence: ${ticket.licence}
        |Violation: 1. ${ticket.violation}
        |
        |Document Confiscated: ${ticket.documentConfiscated}
        |Fine Amount: ${ticket.fineAmount}
        |Patrolling Officer: ${ticket.officerName}
        |
        |
        """.trimMargin()
    }
}
