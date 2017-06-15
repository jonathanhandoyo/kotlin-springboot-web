package com.jonathan.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import javax.annotation.PostConstruct

@Configuration
@EnableAsync
class AsyncConfiguration {
    val log: Logger = LoggerFactory.getLogger(AsyncConfiguration::class.java)

    @PostConstruct
    fun postConstruct() {
        log.info(">> async configured")
    }
}