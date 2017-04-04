package com.jonathan.repositories

import com.jonathan.domain.Event
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface EventRepository : MongoRepository<Event, String> {
    fun findAllByUserId(userId: Long): List<Event>
}