package com.example.ticktingsystem.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticktingsystem.DataModel.TicketData
import com.example.ticktingsystem.LocalDb.TicketDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TicketViewModel(application: Application): AndroidViewModel(application) {
    private val dao = TicketDatabase.getDatabase(application).ticketDao()
    private val _total = MutableStateFlow(0)
    val total = _total.asStateFlow()

    init {
        viewModelScope.launch {
            _total.value = dao.getTotalTickets()
        }
    }

    fun insertTicket(ticket: TicketData) {
        viewModelScope.launch {
            dao.insertTicket(ticket)
        }
    }

}