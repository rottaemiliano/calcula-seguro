package com.example

import mu.KLogger
import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InsuranceApplication

fun main(args: Array<String>) {
	runApplication<InsuranceApplication>(*args)
}

inline fun <reified T : Any> T.klogger(): KLogger {
	val name = if (this::class.isCompanion) {
		T::class.qualifiedName?.removeSuffix(".Companion")
	} else {
		T::class.qualifiedName
	}
	return KotlinLogging.logger(name ?: "")
}


