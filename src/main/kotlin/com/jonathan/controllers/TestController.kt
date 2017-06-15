package com.jonathan.controllers

import com.jonathan.domain.User
import com.jonathan.repositories.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(val userRepository: UserRepository) {
    val log: Logger = LoggerFactory.getLogger(TestController::class.java)

    @GetMapping("/test/{id}")
    fun test(@PathVariable id: Long): User {
        log.info("here!")
        return this.userRepository.findById("usr::$id")
    }
}