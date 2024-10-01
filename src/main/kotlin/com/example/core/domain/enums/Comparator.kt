package com.example.core.domain.enums

import com.example.entrypoint.handler.exception.GenericException

enum class Comparator(val comparatorSymbol: String) {
    EQUAL("=="),
    GREATER(">"),
    LESSER("<"),
    GREATER_OR_EQUAL(">="),
    LESSER_OR_EQUAL("<=");

    companion object {
        fun getByComparator(comparator: String): Comparator {
            return entries
                .firstOrNull { it.comparatorSymbol == comparator }
                ?: throw GenericException("Invalid comparator")
        }
    }
}