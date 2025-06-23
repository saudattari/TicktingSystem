package com.example.ticktingsystem.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticktingsystem.DataModel.TicketData
import com.example.ticktingsystem.LocalDb.TicketDatabase
import com.example.ticktingsystem.LocalDb.TicketRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TicketViewModel(application: Application): AndroidViewModel(application) {
    private val dao = TicketDatabase.getDatabase(application).ticketDao()

    private val _allTickets = MutableStateFlow<List<TicketData>>(emptyList())
    val allTickets: StateFlow<List<TicketData>> get() = _allTickets

    init {
        viewModelScope.launch {
            dao.getAllTickets().collect {
                _allTickets.value = it
            }
        }
    }

    fun insertTicket(ticket: TicketData) {
        viewModelScope.launch {
            dao.insertTicket(ticket)
        }
    }

}