package com.example.entrypoint.request

import com.example.core.domain.Customer

data class CalculateInsuranceRequest(val customer: CustomerRequest) {

    fun toDomain(): Customer {
        return Customer(
            name = this.customer.name,
            document = this.customer.document,
            birthday = this.customer.birthday,
            location = this.customer.location,
            vehicleValue = this.customer.vehicle_value
        )

    }
}