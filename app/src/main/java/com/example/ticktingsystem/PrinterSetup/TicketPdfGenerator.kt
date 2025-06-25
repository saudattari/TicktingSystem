package com.example.ticktingsystem.PrinterSetup

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.ticktingsystem.DataModel.TicketData
import java.io.File
import java.io.FileOutputStream


object TicketPdfGenerator {
    // Add this inside the TicketPdfGenerator class
    fun wrapText(paint: Paint, text: String, maxWidth: Float): List<String> {
        val words = text.split(" ")
        val lines = mutableListOf<String>()
        var currentLine = ""

        for (word in words) {
            val testLine = if (currentLine.isEmpty()) word else "$currentLine $word"
            if (paint.measureText(testLine) <= maxWidth) {
                currentLine = testLine
            } else {
                lines.add(currentLine)
                currentLine = word
            }
        }

        if (currentLine.isNotEmpty()) lines.add(currentLine)
        return lines
    }

    fun drawViolations(
        canvas: Canvas,
        paint: Paint,
        startX: Float,
        startY: Int,
        text: String,
        maxWidth: Float
    ): Int {
        var y = startY

        val violationRegex = "(\\d+\\.\\s)".toRegex()
        val parts = violationRegex.split(text).filter { it.isNotBlank() }
        val matches = violationRegex.findAll(text).map { it.value.trim() }.toList()

        if (parts.isNotEmpty() && matches.isNotEmpty()) {
            val firstLine = "Violation: ${matches[0]}${parts[0]}"
            val lines = wrapText(paint, firstLine, maxWidth)
            lines.forEach {
                canvas.drawText(it, startX, y.toFloat(), paint)
                y += 16
            }

            for (i in 1 until parts.size) {
                val line = "${matches[i]}${parts[i]}"
                val subLines = wrapText(paint, line, maxWidth)
                subLines.forEach {
                    canvas.drawText(it, startX, y.toFloat(), paint)
                    y += 16
                }
            }
        }

        return y
    }


    fun previewTicketAsPdf(context: Context, ticket: TicketData) {
        val pdfDocument = PdfDocument()
        val pageWidth = 300
        val pageHeight = 650
        val pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas

        val paint = Paint().apply {
            typeface = Typeface.createFromAsset(context.assets, "fonts/robotomono.ttf")
            textSize = 9f
            color = Color.BLACK
        }

        var y = 20

        fun drawLine(text: String) {
            canvas.drawText(text, 10f, y.toFloat(), paint)
            y += 16
        }

        fun drawMixedLine(label: String, value: String) {
            val labelPaint = Paint().apply {
                typeface = Typeface.createFromAsset(context.assets, "fonts/ocr.ttf")
                textSize = 10f
                color = Color.BLACK
            }
            val valuePaint = Paint().apply {
                typeface = Typeface.createFromAsset(context.assets, "fonts/bahnschrift.ttf")
                textSize = 9f
                color = Color.BLACK
            }

            canvas.drawText(label, 10f, y.toFloat(), labelPaint)
            val labelWidth = labelPaint.measureText(label)
            canvas.drawText(value, 10f + labelWidth + 2f, y.toFloat(), valuePaint)
            y += 16
        }

        try {
            val logo = BitmapFactory.decodeStream(context.assets.open("logo.png"))
            val scaledLogo = Bitmap.createScaledBitmap(logo, 30, 30, false)
            canvas.drawBitmap(scaledLogo, (pageWidth - 30) / 2f, y.toFloat(), null)
            y += 40
        } catch (e: Exception) {
            e.printStackTrace()
        }

        drawLine("         National Highway & Motorway Police")
        drawLine("              ${ticket.beat}")
        drawLine("")
        drawMixedLine(" Ticket Number: ", ticket.ticketNumber)
        drawMixedLine(" Date: ", ticket.date)
        drawMixedLine(" Time: ", ticket.time)
        drawMixedLine(" Location: ", ticket.location)
        drawMixedLine(" Mode of Payment: ", ticket.paymentMode)
        drawMixedLine(" Vehicle: ", ticket.vehicle)
        drawMixedLine(" Driver's Name: ", ticket.driverName)
        drawMixedLine(" CNIC: ", ticket.cnic)
        drawMixedLine(" Contact Number: ", ticket.contactNumber)
        drawMixedLine(" Licence: ", ticket.licence)
        // Draw formatted violations
        y = drawViolations(canvas, paint, 9f, y, ticket.violation, pageWidth - 20f)

        drawMixedLine(" Document Confiscated: ", ticket.documentConfiscated)
        drawMixedLine(" Fine Amount: ", ticket.fineAmount)
        drawMixedLine(" Patrolling Officer: ", ticket.officerName)

        try {
            val inputStream = context.assets.open("qr.jpg") // or replace with your image path
            val qrBitmap = BitmapFactory.decodeStream(inputStream)
            val scaledQR = Bitmap.createScaledBitmap(qrBitmap, 72, 72, false) // 1 inch = ~72 pixels at 72 DPI
            canvas.drawBitmap(scaledQR, (300 - 72) / 2f, y.toFloat(), null) // center horizontally
            y += 80 // move down for any additional content
        } catch (e: Exception) {
            e.printStackTrace()
        }


        pdfDocument.finishPage(page)

        val file = File(context.cacheDir, "ticket_preview.pdf")
        try {
            pdfDocument.writeTo(FileOutputStream(file))
            pdfDocument.close()

            val uri: Uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                file
            )

            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, "application/pdf")
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or
                        Intent.FLAG_ACTIVITY_NEW_TASK
            }

            context.startActivity(intent)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
