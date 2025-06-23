@file:Suppress("DEPRECATION")

package com.example.ticktingsystem.PrinterSetup

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import androidx.annotation.RequiresPermission
import java.io.OutputStream

object BluetoothPrinterConnection {
    private var socket: BluetoothSocket? = null
    private val printerName = "YourPrinterName" // ← Change this to your thermal printer name

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun getOutputStream(): OutputStream? {
        if (socket != null && socket!!.isConnected) {
            return socket!!.outputStream
        }

        val adapter = BluetoothAdapter.getDefaultAdapter()
        val device: BluetoothDevice? = adapter.bondedDevices.firstOrNull {
            it.name == printerName
        }

        device?.let {
            val uuid = it.uuids[0].uuid
            socket = it.createRfcommSocketToServiceRecord(uuid)
            socket!!.connect()
            return socket!!.outputStream
        }

        return null
    }
}