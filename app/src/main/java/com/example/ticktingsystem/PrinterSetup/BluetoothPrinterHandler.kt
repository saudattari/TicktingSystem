package com.example.ticktingsystem.PrinterSetup


import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
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
                    val scaled = bmp.scale(85, 85, false) // Half inch approx
                    "[C]<img>${PrinterTextParserImg.bitmapToHexadecimalString(printer, scaled)}</img>\n"
                } catch (e: Exception) {
                    ""
                }

                val qr = try {
                    val bmp = BitmapFactory.decodeStream(context.assets.open("qr.jpg"))
                    val scaled = bmp.scale(225, 225, false) // 1 inch approx
                    "[C]<img>${PrinterTextParserImg.bitmapToHexadecimalString(printer, scaled)}</img>\n"
                } catch (e: Exception) {
                    ""
                }

                val formattedText = buildString {
                    append(logo)
                    append("[L]\n")
                    append(bitmapLine(printer, context, "National Highway & Motorway Police", 19f, true, true))
                    append(bitmapLine(printer, context, "", 16f, true))
                    append(bitmapLine(printer, context, ticketData.beat, 16f, true))
                    append("\n")

                    append(bitmapLine(printer, context, "Ticket Number: ${ticketData.ticketNumber}", 16f, heightScale = 2.0f))
                    append(bitmapLine(printer, context, "Date: ${ticketData.date}", 18f, heightScale = 2.0f))
                    append(bitmapLine(printer, context, "Time: ${ticketData.time}", 18f, heightScale = 2.0f))
                    append(bitmapLine(printer, context, "Location: ${ticketData.location}", 18f, heightScale = 2.0f))
                    append(bitmapLine(printer, context, "Mode of Payment: ${ticketData.paymentMode}", 18f, heightScale = 2.0f))
                    append(bitmapLine(printer, context, "Vehicle: ${ticketData.vehicle}", 18f, heightScale = 2.0f))
                    append(bitmapLine(printer, context, "Driver's Name: ${ticketData.driverName}", 18f, heightScale = 2.0f))
                    append(bitmapLine(printer, context, "CNIC: ${ticketData.cnic}", 18f, heightScale = 2.0f))
                    append(bitmapLine(printer, context, "Contact Number: ${ticketData.contactNumber}", 18f, heightScale = 2.0f))
                    append(bitmapLine(printer, context, "License: ${ticketData.licence}", 18f, heightScale = 2.0f))

//                    append("[L]Violation: ${formatViolationsInline(ticketData.violation)}\n")
//                    append(bitmapLine(printer, context, "Violation: ${formatViolations(ticketData.violation)}", 18f))
                    append(bitmapLine(printer, context, "Violation: ${formatViolations(ticketData.violation)}", 18f, heightScale = 2.0f))
                    append(bitmapLine(printer, context, "Document Confiscated: ${ticketData.documentConfiscated}", 18f, heightScale = 2.0f))
                    append(bitmapLine(printer, context, "Fine Amount: ${ticketData.fineAmount}", 18f, heightScale = 2.0f))
                    append(bitmapMultiline(printer, context, "Patrolling Officer", ticketData.officerName, 16f, heightScale = 2.0f))
//                    append(bitmapLine(printer, context, "Patrolling Officer: ${ticketData.officerName}", 16f))

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

    private fun bitmapMultiline(
        printer: EscPosPrinter,
        context: Context,
        title: String,
        content: String,
        textSize: Float,
        bold: Boolean = false,
        heightScale: Float = 1.3f
    ): String {
        val paint = Paint().apply {
            color = Color.BLACK
            this.textSize = textSize
            isAntiAlias = true
            typeface = if (bold) Typeface.DEFAULT_BOLD else Typeface.MONOSPACE
        }

        val width = printer.printerWidthPx
        val padding = 12
        val contentText = "$title $content"

        // Word wrapping logic
        val lines = mutableListOf<String>()
        var currentLine = ""
        for (word in contentText.split(" ")) {
            val testLine = if (currentLine.isEmpty()) word else "$currentLine $word"
            if (paint.measureText(testLine) < width - padding * 2) {
                currentLine = testLine
            } else {
                lines.add(currentLine)
                currentLine = word
            }
        }
        if (currentLine.isNotEmpty()) lines.add(currentLine)

        val lineHeight = (textSize + padding)
        val bitmapHeight = (lineHeight * lines.size * heightScale).toInt()
        val bitmap = Bitmap.createBitmap(width, bitmapHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.WHITE)

        var y = textSize + padding / 2
        for (line in lines) {
            canvas.drawText(line.trim(), 10f, y, paint)
            y += lineHeight * heightScale
        }

        val hex = PrinterTextParserImg.bitmapToHexadecimalString(printer, bitmap, false)
        return "[C]<img>$hex</img>\n"
    }


    private fun bitmapLine(
        printer: EscPosPrinter,
        context: Context,
        text: String,
        textSize: Float,
        center: Boolean = false,
        bold: Boolean = false,
        heightScale: Float = 1.5f
    ): String {
        val paint = Paint().apply {
            color = Color.BLACK
            this.textSize = textSize
            isAntiAlias = true
            typeface = if (bold) Typeface.DEFAULT_BOLD else Typeface.MONOSPACE
        }

        val padding = 10
        val width = printer.printerWidthPx
        val textHeight = (textSize + padding * 2).toInt()
        val height = (textHeight * heightScale).toInt()

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.WHITE)

        val x = if (center) {
            (width - paint.measureText(text)) / 2
        } else {
            10f
        }
        val y = textSize + padding

        canvas.drawText(text, x, y, paint)

        val hexImage = PrinterTextParserImg.bitmapToHexadecimalString(printer, bitmap, false)
        return "[C]<img>$hexImage</img>\n"
    }

    private fun formatViolations(raw: String): String {
        return raw.replace("\n", "  ").replace(Regex("\\s+"), " ")
    }

    private fun formatViolationsInline(text: String): String {
        val lines = text.split(Regex("[\\n\\r]+"))
            .map { it.trim() }
            .filter { it.isNotEmpty() }

        val builder = StringBuilder()
        lines.forEachIndexed { index, line ->
            if (index == 0) {
                builder.append(line)
            } else {
                builder.append("\n$line")
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





