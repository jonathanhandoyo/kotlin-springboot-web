package com.jonathan.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "events")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Event @PersistenceConstructor constructor(
        val id: String,
        val userId: Long,
        val ts: Long,
        val eventCode: String?,
        val eventType: String?,
        val notificationList: Boolean?,
        val onboarding: Boolean?,
        val seenByUser: Boolean?,
        val message: String? = "",
//        val additionalFields: Map<String, Any>?,
//        val requests: Map<String, Any>?,
//        val responses: Map<String, Any>?,
        val docType: String = "events"
)