package com.example.infra.gateway.valorseguro

import com.example.core.domain.InsuranceValue
import com.example.core.gateway.InsuranceValueGateway
import com.example.entrypoint.handler.exception.GenericException
import com.example.infra.gateway.valorseguro.repository.InsuranceValueRepository
import org.springframework.stereotype.Service

@Service
class InsuranceValueGatewayImpl(
    private val insuranceValueRepository: InsuranceValueRepository
): InsuranceValueGateway {

    override fun findAll(): List<InsuranceValue> {
        val results = insuranceValueRepository.findAll()

        if (results.isEmpty()) {
            throw GenericException("No insurance values found")
        }
        return results.map { it.toDomain() }.toList()
    }

}