package com.example.core.domain.enums

import com.example.entrypoint.handler.exception.GenericException

enum class FieldName(val fieldName: String) {
    VEHICLE_VALUE("vehicle_value"),
    LOCATION("location");

    companion object {
        fun getByFieldName(fieldName: String): FieldName {
            return entries
                .firstOrNull { it.fieldName.equals(fieldName, ignoreCase = true) }
                ?: throw GenericException("Invalid field name")
        }
    }
}