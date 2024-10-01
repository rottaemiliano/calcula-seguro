package com.example.infra.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource

@Configuration
class JpaDataSource(
    @Value("\${spring.datasource.url}")
    private val url: String,

    @Value("\${spring.datasource.username}")
    private val username: String,

    @Value("\${spring.datasource.password}")
    private val password: String,

    @Value("\${spring.datasource.driverClassName}")
    private val driverClassName: String
){
    @Bean
    @Primary
    fun buildH2DataSource(): DataSource {
        return DataSourceBuilder.create()
            .url(url)
            .username(username)
            .password(password)
            .driverClassName(driverClassName)
            .build()
    }
}
