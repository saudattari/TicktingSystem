package com.example.ticktingsystem.DataModel

data class Offense(
    val title: String,
    val description: String,
    val minPenalty: Int,
    val maxPenalty: Int,
)

data class scamData(
    val isLocked: Boolean= false,
    val textBased: String = ""
)
