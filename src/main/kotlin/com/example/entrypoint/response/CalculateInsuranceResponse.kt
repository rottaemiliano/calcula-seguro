package com.example.entrypoint.response

import com.example.core.domain.CustomerInsurance

data class CalculateInsuranceResponse(val customer: CustomerResponse) {

    companion object {
        fun fromDomain(customerInsurance: CustomerInsurance): CalculateInsuranceResponse {
            return CalculateInsuranceResponse(
                CustomerResponse(
                    name = customerInsurance.name,
                    location = customerInsurance.location,
                    value = customerInsurance.value
                )
            )
        }
    }
}