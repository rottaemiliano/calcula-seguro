package com.example.core.domain

import java.time.LocalDate

data class Customer(
    val name: String,
    val document: String,
    val birthday: LocalDate,
    val location: String,
    val vehicleValue: Float
)