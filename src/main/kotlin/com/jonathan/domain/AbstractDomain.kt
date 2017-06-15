package com.jonathan.domain;

import com.couchbase.client.java.document.JsonDocument
import com.couchbase.client.java.document.json.JsonObject
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper

interface AbstractDomain {
    fun getDocId(): String
    fun toJsonDocument(): JsonDocument = JsonDocument.create(this.getDocId(), JsonObject.from(ObjectMapper().convertValue<Map<String, *>>(this, object: TypeReference<Map<String, Any>>() {})))
}