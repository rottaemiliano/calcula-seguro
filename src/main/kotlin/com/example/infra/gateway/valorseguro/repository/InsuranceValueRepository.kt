package com.example.infra.gateway.valorseguro.repository

import com.example.infra.gateway.valorseguro.entity.InsuranceValueEntity
import org.springframework.data.jpa.repository.JpaRepository

interface InsuranceValueRepository : JpaRepository<InsuranceValueEntity, Long>