package com.jonathan

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class KotlinWebTestApplication

fun main(args: Array<String>) {
    SpringApplication.run(KotlinWebTestApplication::class.java, *args)
}
