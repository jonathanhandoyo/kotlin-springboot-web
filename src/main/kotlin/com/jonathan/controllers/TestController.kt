package com.jonathan.controllers

import com.jonathan.domain.Event
import com.jonathan.repositories.EventRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController @Autowired constructor(val eventRepository: EventRepository) {
    val log: Logger = LoggerFactory.getLogger(TestController::class.java)

    @GetMapping("/test/{id}")
    fun test(@PathVariable id: Long): List<Event> {
        log.info("here!")
        return this.eventRepository.findAllByUserId(id)
    }
}