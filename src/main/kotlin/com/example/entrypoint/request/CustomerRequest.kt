package com.example.entrypoint.request

import org.springframework.validation.annotation.Validated
import java.time.LocalDate

@Validated
data class CustomerRequest(
    val name: String,
    val document: String,
    val birthday: LocalDate,
    val location: String,
    val vehicle_value: Float
)
