package com.example.entrypoint

import com.example.core.domain.CustomerInsurance
import com.example.core.usecase.CalculateVehicleInsurance
import com.example.entrypoint.handler.ExceptionHandler
import com.example.entrypoint.handler.exception.GenericException
import io.mockk.every
import io.mockk.mockk
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import kotlin.test.Test

class VehicleInsuranceControllerTest {

    private val calculateVehicleInsurance: CalculateVehicleInsurance = mockk<CalculateVehicleInsurance>()
    private val vehicleInsuranceController: VehicleInsuranceController =
        VehicleInsuranceController(calculateVehicleInsurance)

    private val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(vehicleInsuranceController)
        .setMessageConverters(MappingJackson2HttpMessageConverter().apply { this.objectMapper = objectMapper })
        .setControllerAdvice(ExceptionHandler::class.java)
        .build()

    @Test
    fun `should return vehicle insurance response`() {
        // Given
        val expectedResult = CustomerInsurance(
            name = "João",
            location = "BH",
            value = 2800F
        )
        every { calculateVehicleInsurance.execute(any()) }.returns(expectedResult)

        // When
        val response = this.mockMvc
            .perform(
                MockMvcRequestBuilders.post("/insurance/customer/vehicle/calculate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        """
                            {
                                "customer": {
                                    "name": "João",
                                    "document": "123.456.789-10",
                                    "birthday": "1990-07-10",
                                    "location": "BH",
                                    "vehicle_value": 70000
                                }
                            }
                        """
                    )
            )

        // Then
        response
            .andExpect(status().isOk())
            .andExpect(
                content().json(
                    """
                            {
                              "customer": {
                                "name": "João",
                                "location": "BH",
                                "value": 2800.0
                                }
                            }
                        """
                )
            );
    }

    @Test
    fun `should return error when exception is thrown`() {
        // Given
        every { calculateVehicleInsurance.execute(any()) }.throws(GenericException("Error Test"))

        // When
        val response = this.mockMvc
            .perform(
                MockMvcRequestBuilders.post("/insurance/customer/vehicle/calculate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        """
                            {
                                "customer": {
                                    "name": "João",
                                    "document": "123.456.789-10",
                                    "birthday": "1990-07-10",
                                    "location": "BH",
                                    "vehicle_value": 70000
                                }
                            }
                        """
                    )
            )

        // Then
        response
            .andExpect(status().isInternalServerError())
            .andExpect(
                content().json(
                    """
                            {
                              "message": "Error Test"
                            }
                        """
                )
            );
    }
}