package com.example.ticktingsystem.PrinterSetup

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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

    fun previewTicketAsPdf(context: Context, ticket: TicketData) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(300, 600, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas

        val paint = Paint().apply {
            typeface = Typeface.createFromAsset(context.assets, "fonts/robotomono.ttf")
            textSize = 10f
            color = Color.BLACK
        }

        var y = 20

        try {
            val inputStream = context.assets.open("logo.png")
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val scaledBitmap = BitmapFactory.decodeStream(context.assets.open("logo.png")).let {
                Bitmap.createScaledBitmap(it, 30, 30, false)
            }
            canvas.drawBitmap(scaledBitmap, 145f, y.toFloat(), null)
            y += 50
        } catch (e: Exception) {
            e.printStackTrace()
        }

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
                typeface = Typeface.createFromAsset(context.assets, "fonts/bahnschrift2.ttf")
                textSize = 10f
                color = Color.BLACK
            }

            canvas.drawText(label, 10f, y.toFloat(), labelPaint)
            val labelWidth = labelPaint.measureText(label)
            canvas.drawText(value, 10f + labelWidth + 2f, y.toFloat(), valuePaint)
            y += 16
        }

        drawLine("        National Highway & Motorway Police")
        drawLine("             ${ticket.beat}")
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
        drawMixedLine(" Violation: ", ticket.violation)
        drawMixedLine(" Document Confiscated: ", ticket.documentConfiscated)
        drawMixedLine(" Fine Amount: ", ticket.fineAmount)
        drawMixedLine(" Patrolling Officer: ", ticket.officerName)

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