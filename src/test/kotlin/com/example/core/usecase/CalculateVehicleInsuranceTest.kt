package com.example.core.usecase

import com.example.core.domain.Customer
import com.example.core.domain.InsuranceRule
import com.example.core.domain.InsuranceValue
import com.example.core.gateway.InsuranceValueGateway
import com.example.entrypoint.handler.exception.GenericException
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class CalculateVehicleInsuranceTest {

    private val insuranceValueGateway: InsuranceValueGateway = mockk<InsuranceValueGateway>()
    private val calculateVehicleInsurance: CalculateVehicleInsurance =
        CalculateVehicleInsurance(insuranceValueGateway)

    @Test
    fun `should return a insurance value when rules match`() {
        // Given
        val insuranceValuesMock = listOf(
            InsuranceValue(
                insuranceRule = listOf(
                    InsuranceRule(
                        fieldName = "vehicle_value",
                        operation = "<=",
                        referenceValue = "70000"
                    )
                ),
                percentage = 4.0F
            )
        )
        val customerMock = Customer(
            name = "João",
            document = "123.456.789-10",
            birthday = LocalDate.of(1990, 7, 10),
            location = "BH",
            vehicleValue = 70000F
        )

        every { insuranceValueGateway.findAll() }.returns(insuranceValuesMock)

        // When
        val result = calculateVehicleInsurance.execute(customerMock)

        // Then
        assertEquals("João", result.name)
        assertEquals("BH", result.location)
        assertEquals(2800F, result.value)
    }

    @Test
    fun `should return a insurance value when one of many rules match`() {
        // Given
        val insuranceValuesMock = listOf(
            InsuranceValue(
                insuranceRule = listOf(
                    InsuranceRule(
                        fieldName = "vehicle_value",
                        operation = ">",
                        referenceValue = "10000"
                    ),
                    InsuranceRule(
                        fieldName = "location",
                        operation = "==",
                        referenceValue = "SP"
                    )
                ),
                percentage = 6.0F
            ),
            InsuranceValue(
                insuranceRule = listOf(
                    InsuranceRule(
                        fieldName = "vehicle_value",
                        operation = ">=",
                        referenceValue = "10000"
                    ),
                    InsuranceRule(
                        fieldName = "location",
                        operation = "==",
                        referenceValue = "SP"
                    )
                ),
                percentage = 4.0F
            ),
            InsuranceValue(
                insuranceRule = listOf(
                    InsuranceRule(
                        fieldName = "vehicle_value",
                        operation = "<",
                        referenceValue = "10000"
                    ),
                    InsuranceRule(
                        fieldName = "location",
                        operation = "==",
                        referenceValue = "SP"
                    )
                ),
                percentage = 4.0F
            ),
            InsuranceValue(
                insuranceRule = listOf(
                    InsuranceRule(
                        fieldName = "location",
                        operation = "==",
                        referenceValue = "BH"
                    )
                ),
                percentage = 5.0F
            )
        )
        val customerMock = Customer(
            name = "João",
            document = "123.456.789-10",
            birthday = LocalDate.of(1990, 7, 10),
            location = "BH",
            vehicleValue = 70000F
        )

        every { insuranceValueGateway.findAll() }.returns(insuranceValuesMock)

        // When
        val result = calculateVehicleInsurance.execute(customerMock)

        // Then
        assertEquals("João", result.name)
        assertEquals("BH", result.location)
        assertEquals(3500F, result.value)
    }

    @Test
    fun `should throw exception when no rules match`() {
        // Given
        val insuranceValuesMock = listOf(
            InsuranceValue(
                insuranceRule = listOf(
                    InsuranceRule(
                        fieldName = "vehicle_value",
                        operation = "<=",
                        referenceValue = "70000"
                    )
                ),
                percentage = 0.04F
            )
        )
        val customerMock = Customer(
            name = "João",
            document = "123.456.789-10",
            birthday = LocalDate.of(1990, 7, 10),
            location = "BH",
            vehicleValue = 70001F
        )

        every { insuranceValueGateway.findAll() }.returns(insuranceValuesMock)

        // When
        assertThrows<GenericException> { calculateVehicleInsurance.execute(customerMock) }

    }
}