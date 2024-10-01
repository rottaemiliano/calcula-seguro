package com.example.infra.gateway.valorseguro.entity

import com.example.core.domain.InsuranceValue
import com.example.infra.gateway.regraseguro.entity.InsuranceRuleEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinColumns
import jakarta.persistence.OneToMany

@Entity(name = "insurance_value")
class InsuranceValueEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0,

    @OneToMany(orphanRemoval = true, cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumns(JoinColumn(name = "insurance_value_id", referencedColumnName = "id", updatable = false))
    private val insuranceRules: List<InsuranceRuleEntity>,

    private val percentage: Float,
) {

    fun toDomain(): InsuranceValue {
        return InsuranceValue(
            insuranceRule = insuranceRules.map { it.toDomain() }.toList(),
            percentage = percentage
        )
    }
}
