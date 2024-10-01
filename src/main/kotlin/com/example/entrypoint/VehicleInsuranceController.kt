package com.example.entrypoint

import com.example.core.usecase.CalculateVehicleInsurance
import com.example.entrypoint.request.CalculateInsuranceRequest
import com.example.entrypoint.response.CalculateInsuranceResponse
import com.example.klogger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/insurance/customer/vehicle")
class VehicleInsuranceController(
    private val calculateVehicleInsurance : CalculateVehicleInsurance,
) {

    @PostMapping("/calculate")
    fun calculateCustomerVehicleInsurance(
        @RequestBody request: CalculateInsuranceRequest
    ): ResponseEntity<CalculateInsuranceResponse> {

        logger.info { "Received request to calculate vehicle insurance for customer=${request.customer.name}" }
        val response = calculateVehicleInsurance.execute(request.toDomain())

        return ResponseEntity.ok(CalculateInsuranceResponse.fromDomain(response))
    }

    companion object {
        private val logger = klogger()
    }
}