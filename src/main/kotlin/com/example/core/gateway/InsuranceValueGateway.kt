package com.example.core.gateway

import com.example.core.domain.InsuranceValue

interface InsuranceValueGateway {
    fun findAll(): List<InsuranceValue>
}