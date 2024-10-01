package com.example.infra.gateway.regraseguro.entity

import com.example.core.domain.InsuranceRule
import com.example.infra.gateway.valorseguro.entity.InsuranceValueEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity(name = "insurance_rule")
class InsuranceRuleEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0,

    @ManyToOne
    private val insuranceValue: InsuranceValueEntity?,

    private val referenceValue: String,

    private val operation: String,

    private val fieldName: String,
) {

    fun toDomain(): InsuranceRule {
        return InsuranceRule(
            referenceValue = referenceValue,
            operation = operation,
            fieldName = fieldName,
        )
    }
}
