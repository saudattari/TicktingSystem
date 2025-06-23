package com.example.ticktingsystem.LocalDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ticktingsystem.DataModel.TicketData
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicket(ticket: TicketData)

    @Query("SELECT COUNT(*) FROM tickets")
    suspend fun getTotalTickets(): Int
}