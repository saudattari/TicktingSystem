package com.example.ticktingsystem.PrinterSetup


import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.dantsu.escposprinter.EscPosPrinter
import com.dantsu.escposprinter.connection.bluetooth.BluetoothConnection
import com.dantsu.escposprinter.connection.bluetooth.BluetoothPrintersConnections
import com.dantsu.escposprinter.textparser.PrinterTextParserImg
import com.example.ticktingsystem.DataModel.TicketData
import androidx.core.graphics.scale

object BluetoothPrinterHandler {

    fun printTicketIfAvailable(context: Context, ticketData: TicketData) {
        val activity = context as? Activity ?: return

        checkBluetoothPermissions(activity) {
            val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

            if (bluetoothAdapter == null) {
                Toast.makeText(context, "Bluetooth not supported on this device", Toast.LENGTH_LONG).show()
                return@checkBluetoothPermissions
            }

            if (!bluetoothAdapter.isEnabled) {
                Toast.makeText(context, "Bluetooth is turned off", Toast.LENGTH_LONG).show()
                return@checkBluetoothPermissions
            }

            val printerConnection: BluetoothConnection? = BluetoothPrintersConnections.selectFirstPaired()
            if (printerConnection == null) {
                Toast.makeText(context, "No paired Bluetooth printer found", Toast.LENGTH_LONG).show()
                return@checkBluetoothPermissions
            }

            try {
                val printer = EscPosPrinter(printerConnection, 203, 57f, 32)

                val logo = try {
                    val bmp = BitmapFactory.decodeStream(context.assets.open("logo2.jpg"))
                    val scaled = bmp.scale(70, 70, false) // Half inch approx
                    "[C]<img>${PrinterTextParserImg.bitmapToHexadecimalString(printer, scaled)}</img>\n"
                } catch (e: Exception) {
                    ""
                }

                val qr = try {
                    val bmp = BitmapFactory.decodeStream(context.assets.open("qr.jpg"))
                    val scaled = bmp.scale(180, 180, false) // 1 inch approx
                    "[C]<img>${PrinterTextParserImg.bitmapToHexadecimalString(printer, scaled)}</img>\n"
                } catch (e: Exception) {
                    ""
                }

                val formattedText = buildString {
                    append(logo)
                    append("[L]\n")
                    append("[C]National Highway & Motorway Police\n")
                    append("[C]${ticketData.beat}\n")
                    append("[L]\n")

                    append("[L]Ticket Number: ${ticketData.ticketNumber}\n")
                    append("[L]Date: ${ticketData.date}\n")
                    append("[L]Time: ${ticketData.time}\n")
                    append("[L]Location: ${ticketData.location}\n")
                    append("[L]Mode of Payment: ${ticketData.paymentMode}\n")
                    append("[L]Vehicle: ${ticketData.vehicle}\n")
                    append("[L]Driver's Name: ${ticketData.driverName}\n")
                    append("[L]CNIC: ${ticketData.cnic}\n")
                    append("[L]Contact Number: ${ticketData.contactNumber}\n")
                    append("[L]License: ${ticketData.licence}\n")

                    append("[L]Violation: ${formatViolationsInline(ticketData.violation)}\n")
                    append("[L]Document Confiscated: ${ticketData.documentConfiscated}\n")
                    append("[L]Fine Amount: ${ticketData.fineAmount}\n")
                    append("[L]Patrolling Officer: ${ticketData.officerName}\n")

                    append("[L]\n")
                    append(qr)
                }

                printer.printFormattedTextAndCut(formattedText)

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Print failed: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun formatViolationsInline(text: String): String {
        val lines = text.split(Regex("[\\n\\r]+"))
            .map { it.trim() }
            .filter { it.isNotEmpty() }

        val builder = StringBuilder()
        lines.forEachIndexed { index, line ->
            if (index == 0) {
                builder.append("${index + 1}. $line")
            } else {
                builder.append("\n${index + 1}. $line")
            }
        }
        return builder.toString()
    }


    fun checkBluetoothPermissions(activity: Activity, onGranted: () -> Unit) {
        val permissions = mutableListOf<String>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.BLUETOOTH_CONNECT)
            }
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.BLUETOOTH_SCAN)
            }
        } else {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.BLUETOOTH)
            }
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.BLUETOOTH_ADMIN)
            }
        }

        if (permissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(activity, permissions.toTypedArray(), 999)
        } else {
            onGranted()
        }
    }
}





