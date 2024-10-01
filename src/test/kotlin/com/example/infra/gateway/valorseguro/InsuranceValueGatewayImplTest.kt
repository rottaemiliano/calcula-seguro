package com.example.infra.gateway.valorseguro

import com.example.entrypoint.handler.exception.GenericException
import com.example.infra.gateway.regraseguro.entity.InsuranceRuleEntity
import com.example.infra.gateway.valorseguro.entity.InsuranceValueEntity
import com.example.infra.gateway.valorseguro.repository.InsuranceValueRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InsuranceValueGatewayImplTest {

    private val insuranceValueRepository: InsuranceValueRepository = mockk<InsuranceValueRepository>()
    private val insuranceValueGatewayImpl: InsuranceValueGatewayImpl =
        InsuranceValueGatewayImpl(insuranceValueRepository)


    @Test
    fun `should return a list of insurance values`() {
        // Given
        val expectedResults = listOf(
            InsuranceValueEntity(
                insuranceRules = listOf(
                    InsuranceRuleEntity(
                        id = 1,
                        insuranceValue = null,
                        fieldName = "location",
                        operation = "==",
                        referenceValue = "SP"
                    ),
                    InsuranceRuleEntity(
                        id = 2,
                        insuranceValue = null,
                        fieldName = "vehicle_value",
                        operation = "<=",
                        referenceValue = "70000"
                    )
                ),
                percentage = 0.05F
            )
        )
        every { insuranceValueRepository.findAll() }.returns(expectedResults)

        // When
        val result = insuranceValueGatewayImpl.findAll()

        // Then
        assertEquals(1, result.size)
        assertEquals(2, result.first().insuranceRule.size)

        assertEquals("location", result.first().insuranceRule[0].fieldName)
        assertEquals("==", result.first().insuranceRule[0].operation)
        assertEquals("SP", result.first().insuranceRule[0].referenceValue)

        assertEquals("vehicle_value", result.first().insuranceRule[1].fieldName)
        assertEquals("<=", result.first().insuranceRule[1].operation)
        assertEquals("70000", result.first().insuranceRule[1].referenceValue)
    }

    @Test
    fun `should throw exception when return a empty list of insurance values`() {
        // Given
        every { insuranceValueRepository.findAll() }.returns(emptyList())

        // When
        assertThrows<GenericException> { insuranceValueGatewayImpl.findAll() }
    }
}