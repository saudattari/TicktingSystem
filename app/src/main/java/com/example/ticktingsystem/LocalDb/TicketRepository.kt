package com.example.ticktingsystem.LocalDb

import com.example.ticktingsystem.DataModel.TicketData
import kotlinx.coroutines.flow.Flow

class TicketRepository(private val dao: TicketDao) {
    suspend fun insertTicket(ticket: TicketData) = dao.insertTicket(ticket)
    fun getAllTickets(): Flow<List<TicketData>> = dao.getAllTickets()
}