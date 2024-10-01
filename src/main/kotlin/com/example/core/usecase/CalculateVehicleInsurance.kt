package com.example.core.usecase

import com.example.core.domain.Customer
import com.example.core.domain.CustomerInsurance
import com.example.core.domain.InsuranceValue
import com.example.core.domain.enums.Comparator
import com.example.core.domain.enums.Comparator.EQUAL
import com.example.core.domain.enums.Comparator.GREATER
import com.example.core.domain.enums.Comparator.GREATER_OR_EQUAL
import com.example.core.domain.enums.Comparator.LESSER
import com.example.core.domain.enums.Comparator.LESSER_OR_EQUAL
import com.example.core.domain.enums.FieldName
import com.example.core.domain.enums.FieldName.LOCATION
import com.example.core.domain.enums.FieldName.VEHICLE_VALUE
import com.example.core.gateway.InsuranceValueGateway
import com.example.entrypoint.handler.exception.GenericException
import com.example.klogger
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode

private const val ZERO = 0

@Service
class CalculateVehicleInsurance(
    private val insuranceValueGateway: InsuranceValueGateway,
) {
    fun execute(customer: Customer): CustomerInsurance {
        val insurancePercentage = insuranceValueGateway.findAll()
            .sortedByDescending { it.insuranceRule.size }
            .firstOrNull { verifyRulesMatch(customer, it) }

        if (insurancePercentage == null) {
            throw GenericException("Value does not match any insurance rule")
        }

        logger.info { "Insurance percentage for customer=${customer.name}, location=${customer.location}, vehicle_value=${customer.vehicleValue} is $insurancePercentage" }
        return CustomerInsurance(
            name = customer.name,
            location = customer.location,
            value = calculateInsuranceValue(insurancePercentage, customer)
        )
    }

    private fun calculateInsuranceValue(insurancePercentage: InsuranceValue, customer: Customer): Float {
        val value = ((insurancePercentage.percentage / 100) * customer.vehicleValue).toDouble()
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).toFloat()
    }

    private fun verifyRulesMatch(customer: Customer, insuranceValue: InsuranceValue): Boolean {
        val validations = mutableListOf<Boolean>()
        insuranceValue.insuranceRule.forEach { rule ->
            val isMatch = when (FieldName.getByFieldName(rule.fieldName)) {
                VEHICLE_VALUE ->
                    verifyValueOperationMatch(
                        customer.vehicleValue,
                        rule.referenceValue.toFloat(),
                        Comparator.getByComparator(rule.operation)
                    )

                LOCATION ->
                    verifyLocationOperationMatch(customer.location, rule.referenceValue)
            }

            validations.add(isMatch)
            if (!isMatch) return@forEach
        }
        return validations.all { it }
    }

    private fun verifyValueOperationMatch(vehicleValue: Float, referenceValue: Float, comparator: Comparator): Boolean {
        return when (comparator) {
            EQUAL -> vehicleValue.compareTo(referenceValue) == ZERO

            GREATER -> vehicleValue.compareTo(referenceValue) > ZERO

            LESSER -> vehicleValue.compareTo(referenceValue) < ZERO

            GREATER_OR_EQUAL -> vehicleValue.compareTo(referenceValue) == ZERO || vehicleValue.compareTo(referenceValue) > ZERO

            LESSER_OR_EQUAL -> vehicleValue.compareTo(referenceValue) == ZERO || vehicleValue.compareTo(referenceValue) < ZERO
        }
    }

    private fun verifyLocationOperationMatch(location: String, referenceValue: String): Boolean {
        return location == referenceValue
    }

    companion object {
        private val logger = klogger()
    }
}


